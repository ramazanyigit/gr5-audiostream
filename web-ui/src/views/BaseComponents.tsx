import styled from "styled-components";

const RowContainer = styled.div`
  border: 1px solid #ccc;
  padding: 0.5rem 1rem;
  margin-bottom: 0.25rem;
`;

const HoverableIcon = styled.i`
  &:hover {
    color: #007bff !important;
    font-size: 1rem;
    cursor: pointer;
  }
`;

const HoverableRowContainer = styled(RowContainer)`
  &:hover {
    background: #fafafa;
    cursor: pointer;
  }
`;

export { RowContainer, HoverableIcon, HoverableRowContainer};
