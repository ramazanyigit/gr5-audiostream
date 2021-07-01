const prod = {
    url: {
      KEYCLOAK_BASE_URL: 'http://localhost:8080'
    }
  }
  
  const dev = {
    url: {
      KEYCLOAK_BASE_URL: 'http://localhost:8763',
    }
  }
  
  export const config = process.env.NODE_ENV === 'development' ? dev : prod