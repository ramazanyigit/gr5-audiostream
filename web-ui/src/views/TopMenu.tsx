import { Container, Navbar, Nav } from "react-bootstrap";
import { Link, useLocation } from "react-router-dom";

export default function TopMenu() {
  const { pathname } = useLocation();
  return (
    <div className="as-header">
      <Navbar bg="black" expand="lg" variant="dark">
        <Container fluid className="px-0">
          <Navbar.Brand href="/" className="mr-4">
            AUDIOSTREAM
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link to="/" as={Link} active={pathname === "/"}>
                Home
              </Nav.Link>
              <Nav.Link to="/search" as={Link} active={pathname === "/search"}>
                Search
              </Nav.Link>
              <Nav.Link to="/top-100" as={Link} active={pathname === "/top-100"}>
                Top 100 Songs
              </Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </div>
  );
}
