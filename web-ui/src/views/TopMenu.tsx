import { Container, Navbar, Nav, NavDropdown } from "react-bootstrap";
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
              <NavDropdown title="Playlists" id="basic-nav-dropdown">
                <NavDropdown.Item href="#action/3.1">
                  Playlist 1
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.2">
                  Playlist 2
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.3">
                  Playlist 3
                </NavDropdown.Item>
                <NavDropdown.Divider />
                <NavDropdown.Item href="#action/3.4">
                  <i className="fa fa-plus-circle"></i> Create playlist
                </NavDropdown.Item>
              </NavDropdown>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </div>
  );
}
