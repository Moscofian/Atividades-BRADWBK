| Verbo HTTP | Endpoints (path)  | Descrição                        | Input                       | Outputs                     | Cód. Sucesso | Cód. Falha                      |
| ---------- | ----------------- | -------------------------------- | --------------------------- | --------------------------- | ------------ | ------------------------------- |
| GET        | `/postagens`      | Listagem de todas as postagens   | -                           | Lista de objetos JSON       | 200 OK       | 500 Internal Server Error       |
| POST       | `/postagens`      | Criação de uma nova postagem     | JSON (`titulo`, `conteudo`) | Objeto JSON criado          | 201 Created  | 400 Bad Request                 |
| GET        | `/postagens/{id}` | Busca de uma postagem específica | -                           | Objeto JSON único           | 200 OK       | 404 Not Found                   |
| PUT        | `/postagens/{id}` | Atualização de uma postagem      | JSON (`titulo`, `conteudo`) | Objeto JSON atualizado      | 200 OK       | 400 Bad Request / 404 Not Found |
| DELETE     | `/postagens/{id}` | Remoção de uma postagem          | -                           | Objeto deletado ou mensagem | 200 OK       | 404 Not Found                   |
