import { Button, Modal, Form } from "react-bootstrap";
import { useState } from "react";

interface PlaylistAddModalProps {
  show: boolean;
  handleClose?: () => void;
  onSubmit: (data: { id?: string; name: string }) => void;
}

export default function PlaylistAddModal({
  show,
  handleClose,
  onSubmit,
}: PlaylistAddModalProps) {
  const [name, setName] = useState("");
  return (
    <Form
      onSubmit={(e) => {
        e.preventDefault();
        onSubmit({
          name,
        });
      }}
    >
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Create a playlist</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form.Group className="mb-3" controlId="playlistName">
            <Form.Label>Playlist Name</Form.Label>
            <Form.Control
              type="text"
              required
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </Form.Group>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose} type="button">
            Close
          </Button>
          <Button
            variant="primary"
            onClick={() => {
              onSubmit({
                name,
              });
            }}
          >
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </Form>
  );
}
