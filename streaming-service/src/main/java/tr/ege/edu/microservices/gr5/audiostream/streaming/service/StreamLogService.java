package tr.ege.edu.microservices.gr5.audiostream.streaming.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import tr.ege.edu.microservices.gr5.audiostream.streaming.exception.StreamingException;
import tr.ege.edu.microservices.gr5.audiostream.streaming.feign.CollectionFeignProxy;
import tr.ege.edu.microservices.gr5.audiostream.streaming.feign.SongDetail;
import tr.ege.edu.microservices.gr5.audiostream.streaming.model.KafkaEvent;
import tr.ege.edu.microservices.gr5.audiostream.streaming.model.StreamLog;
import tr.ege.edu.microservices.gr5.audiostream.streaming.repository.StreamLogRepository;
import tr.ege.edu.microservices.gr5.audiostream.streaming.util.AuthenticationUtil;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StreamLogService {
    private final CollectionFeignProxy feignProxy;
    private final StreamLogRepository repository;
    private final KafkaProducer kafkaProducer;
    private final JedisPool jedisPool;

    public Optional<StreamLog> findById(@NotNull UUID id) {
        return repository.findById(id);
    }

    public List<StreamLog> getLogs() {
        return repository.findAll(Sort.by(Sort.Order.desc("creationTimestamp")));
    }

    public List<SongDetail> getLast5Logs(UUID userId) {
        List<SongDetail> list = new ArrayList<>();
        for (StreamLog log : repository.getTop5ByUserIdOrderByCreationTimestampDesc(userId)) {
            try {
                list.add(feignProxy.getSong(log.getSongId()));
            } catch (Exception ignored) {
                list.add(new SongDetail(log.getSongId(), "Unknown", null, 0.0f));
            }
        }

        return list;
    }

    public StreamLog stop(@NotNull UUID id) throws StreamingException {
        return stop(id, null);
    }

    public StreamLog stop(@NotNull UUID id, Float stopOffset) throws StreamingException {
        Optional<StreamLog> entityOptional = repository.findById(id);

        if (entityOptional.isEmpty()) {
            throw new StreamingException("There is no record by given id.");
        }

        StreamLog entity = entityOptional.get();

        if (!entity.getUserId().equals(AuthenticationUtil.getId())) {
            throw new StreamingException("This record is not belongs to the user.");
        }

        entity.setStopOffset(stopOffset);
        entity.setEndTimestamp(OffsetDateTime.now());

        final var result = repository.save(entity);
        jedisPool.getResource().del(entity.getUserId().toString());
        kafkaProducer.send(KafkaEvent.of("STOP", result));
        return result;
    }

    public StreamLog start(UUID userId, UUID songId, Float playOffset) throws StreamingException {
        var redis = jedisPool.getResource();

        String userIdString = userId.toString();
        if (redis.exists(userIdString)) {
            stop(UUID.fromString(redis.get(userIdString)), 0.0f);
        }

        var entity = new StreamLog();
        entity.setUserId(userId);
        entity.setSongId(songId);
        entity.setCreationTimestamp(OffsetDateTime.now());
        entity.setPlayOffset(playOffset);

        final var result = repository.save(entity);
        redis.set(entity.getUserId().toString(), entity.getId().toString());
        kafkaProducer.send(KafkaEvent.of("START", result));

        return result;
    }

    public SongDetail getCurrentStreamingById(UUID userId) {
        var redis = jedisPool.getResource();

        String userIdString = userId.toString();
        if (!redis.exists(userIdString)) {
            return null;
        }

        var logOptional = repository.findById(UUID.fromString(redis.get(userIdString)));

        if (logOptional.isEmpty()) {
            return null;
        }

        var log = logOptional.get();
        try {
            return feignProxy.getSong(log.getSongId());
        } catch (Exception ignored) {
            return new SongDetail(log.getSongId(), "Unknown", null, 0.0f);
        }
    }
}
