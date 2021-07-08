const DurationUtil = {
  numberToHumanReadable: (duration: number | undefined) => {
    if (!duration) {
      duration = 0;
    }

    const seconds = duration % 60;
    const minutes = (duration / 60).toFixed(0);

    return `${minutes}:${seconds < 10 ? "0" : ""}${seconds.toFixed(0)}`;
  },
};

export default DurationUtil;
