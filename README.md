# 🛠️ Sistema de Gerenciamento de Aluguéis (Backend)

API robusta desenvolvida em **Spring Boot** para controle de locação de equipamentos, com foco em integridade de dados e regras de negócio complexas.

## 🚀 Funcionalidades Implementadas

### 1. Gestão de Aluguéis e Devoluções
* **Cálculo de Multa Automático**: O sistema detecta atrasos e aplica uma multa de **20% sobre a diária** por cada dia de atraso.
* **Relatório de Atrasados**: Endpoint que filtra automaticamente aluguéis com status `ABERTO` e data prevista vencida.
* **Registro de Data Real**: Armazenamento preciso da data de devolução para fins de auditoria e faturamento.

### 2. Integridade e Segurança (Backend Pro)
* **Global Exception Handler**: Tratamento centralizado de erros para evitar vazamento de logs técnicos (stacktraces) no Postman.
* **Proteção de Dados (LGPD)**: Validação de CPF único com mensagens genéricas para evitar a enumeração de usuários.
* **Injeção de Dependências**: Arquitetura limpa utilizando `Service Layer` para isolar a lógica de negócio dos Controllers.

### 3. Controle de Inventário Inteligente
* **Soft Delete (Exclusão Lógica)**: Equipamentos nunca são apagados fisicamente para não quebrar o histórico financeiro. O sistema utiliza uma flag `ativo`.
* **Filtros de Disponibilidade**: Busca otimizada que retorna apenas itens que estão ativos e não ocupados no momento.
* **Relatório Geral**: Acesso administrativo a todo o banco de dados para controle de estoque total.

## 📊 Exemplo de Regra de Negócio
No teste real, um aluguel com base de **R$ 250,00** e 48 dias de atraso foi recalculado automaticamente para **R$ 730,00**, garantindo a lucratividade da operação.
