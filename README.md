Bank Quarkus - Sistema de Gestão de Agências Bancárias

Um projeto completo de simulação de sistema bancário desenvolvido com Quarkus, focado em conceitos modernos do ecossistema Java, arquitetura de microsserviços, persistência, testes automatizados, observabilidade e containerização.

Este repositório contém a implementação de um sistema para cadastro, alteração, consulta e remoção de agências bancárias (operações CRUD), simulando cenários reais encontrados 
em empresas do setor financeiro e fintechs.

Além do desenvolvimento das APIs REST, a aplicação consome serviços externos via RestClient, gerencia persistência de dados com Hibernate ORM / Panache,
implementa métricas de observabilidade exportadas para Prometheus e Grafana, e conta com ambiente isolado via Docker Compose.

Tecnologias e Ferramentas Utilizadas

Java 21+

Quarkus (Framework Java Supersonic Subatomic)

Jakarta EE / RESTEasy Reactive (Criação de APIs REST)

Quarkus REST Client (Consumo de APIs externas)

Hibernate ORM com Panache (Repository Pattern) (Persistência e ORM)

PostgreSQL (Banco de dados relacional)

JUnit 5 & Mockito (Testes de unidade e mocks)

Micrometer, Prometheus & Grafana (Observabilidade e Métricas)

Quarkus Logging (io.quarkus.logging.Log) (Logs da aplicação)

Docker & Docker Compose (Containerização e orquestração do ambiente)

Funcionalidades Principais

Gestão de Agências Bancárias (CRUD REST):

Cadastrar novas agências com validações e dados cadastrais.

Listar e buscar agências por ID.

Atualizar dados de agências existentes.

Deletar registros de agências.

Consumo de Serviços Externos:

Integração com APIs externas utilizando @RegisterRestClient para validação e busca de situações cadastrais.

Persistência com Panache (Padrão Repository):

Abstração de operações de banco de dados (insert, select, update, delete).

Uso de variáveis de ambiente configuráveis no application.properties.

Testes Automatizados:

Cobertura de regras de negócio com testes unitários isolados usando JUnit e Mockito.

Observabilidade e Monitoramento:

Métricas customizadas e de sistema expostas via Micrometer.

Coleta de dados com Prometheus.

Dashboards gráficos interativos no Grafana.

Logs estruturados para auditoria e acompanhamento em tempo real.
