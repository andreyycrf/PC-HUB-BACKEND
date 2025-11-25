# Problemas e Melhorias Identificadas no Projeto PC-HUB

O projeto PC-HUB foi analisado com base na documentação fornecida e na estrutura de código. A seguir, estão listados os principais problemas e as melhorias propostas, focando inicialmente em segurança e nas funcionalidades básicas (CRUD).

## 1. Problemas Críticos (Erros)

| ID | Categoria | Descrição do Problema | Impacto |
| :--- | :--- | :--- | :--- |
| **E01** | **Segurança** | **Ausência de Spring Security:** O projeto foi enviado como "PC-HUB-sem-security". Não há configuração de autenticação (login/senha) ou autorização, deixando todas as rotas e dados acessíveis publicamente. | **Crítico.** Viola o requisito RF22 (Gerenciamento de Usuário) e compromete a privacidade dos dados do usuário. |
| **E02** | **Configuração** | **Versão do Java:** O `pom.xml` especifica Java 11, mas o ambiente de execução pode estar usando uma versão diferente (o ambiente padrão é 11, mas é bom garantir). | **Alto.** Pode causar erros de compilação ou execução se a versão não for compatível. |
| **E03** | **Persistência** | **Configuração de Banco de Dados:** O `application.properties` está configurado para H2 em memória (`jdbc:h2:mem:pcdb`). Isso significa que todos os dados serão perdidos ao reiniciar a aplicação. A dependência do MySQL está presente, mas não configurada. | **Alto.** Impede a persistência real dos dados, tornando o sistema inútil para uso contínuo. |
| **E04** | **Código (Geral)** | **Erros de Compilação/Execução:** É muito provável que o código contenha erros de sintaxe, lógica ou referências a classes/métodos inexistentes, dado o relato do usuário. Isso será verificado na próxima fase. | **Alto.** Impede o funcionamento básico do sistema. |

## 2. Melhorias Propostas (Aprimoramento)

| ID | Categoria | Descrição da Melhoria | Requisito Relacionado |
| :--- | :--- | :--- | :--- |
| **M01** | **Segurança** | **Implementação de Spring Security:** Adicionar dependência e configurar o Spring Security para: 1. Autenticação de usuários (Login/Logout). 2. Proteção de rotas (apenas usuários autenticados podem acessar as funcionalidades de gestão). 3. Criptografia de senhas (BCrypt). | RF22 (Gerenciamento de Usuário) |
| **M02** | **Persistência** | **Configuração para MySQL (ou H2 persistente):** Configurar o banco de dados para persistência, utilizando MySQL (já que a dependência está no `pom.xml`) ou H2 em modo de arquivo, para que os dados não sejam perdidos. | RF01-RF21 (Todos os requisitos de CRUD) |
| **M03** | **Validação** | **Implementação da Lógica de Compatibilidade:** Desenvolver a lógica no `PecaService` ou `ComponenteDataService` para verificar a compatibilidade entre peças (ex: CPU e Placa-Mãe, RAM e Placa-Mãe, Fonte e consumo total). | RF02 (Associar Componentes) e RF03 (Validar campos) |
| **M04** | **Estrutura** | **Refatoração e Padrões de Código:** Revisar as classes (Model, Service, Controller) para garantir que sigam os padrões de Spring Boot e Java (ex: injeção de dependência, uso correto de anotações, separação de responsabilidades). | Qualidade de Software |
| **M05** | **Frontend** | **Melhoria da Usabilidade e Estética:** O frontend (HTML/CSS/JS) parece ser básico. Melhorar a interface do usuário (UI) e a experiência do usuário (UX) para torná-lo mais profissional e intuitivo. | Requisito Não Funcional (Usabilidade) |

## Plano de Ação para Correção (Fases 4 e 5)

1. **Configuração Inicial:** Configurar o ambiente de desenvolvimento (Fase 4).
2. **Correção de Dependências:** Garantir que o projeto compile e rode com o H2 em memória (E02, E03).
3. **Correção de CRUD:** Corrigir erros de código (E04) para que as funcionalidades básicas de cadastro e listagem de PC e Peças funcionem (RF01, RF008).
4. **Implementação de Segurança:** Adicionar Spring Security (M01, E01).
5. **Implementação de Lógica:** Adicionar a lógica de compatibilidade (M03).
6. **Refatoração:** Aplicar melhorias de código (M04).
7. **Frontend:** Melhorar a interface (M05).
8. **Persistência:** Configurar para MySQL ou H2 persistente (M02).
