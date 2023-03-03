# MarketControl
Sistema de controle de estoque.

## Endpoints
- [Cadastro de produto](#cadastrar-produto)
    - Cadastrar
    - Listar todos
    - Apagar
    - Alterar
    - [Mostrar os detalhes](#detalhar-produto)

- [Cadastro de estabelecimento](#cadastrar-estabelecimento)
    - Cadastrar
    - Listar todos
    - Apagar
    - Alterar
    
- [Analise de estoque](#analise-de-estoque)
    - Listar produtos
    
- [Cadastro de responsavel](#cadastrar-responsavel)
    - Cadastrar

- [Perfil do responsavel](#perfil-do-responsavel)
    - Alterar
    - Mostrar os detalhes

--- 

## Cadastrar Produto 
`POST` /marketcontrol/api/produto

| campo | tipo | obrigatorio | descricao 
|-----|:----:|:-----------:|---------
| valor | decimal | sim | eh o valor do produto. deve ser maior que zero 
| categoria_id | int | sim | eh o id de uma categoria previamente cadastrada 
| data_cadastro | data | sim | eh a data do cadastro
| data_validade | data | sim | eh a data de validade
| quantidade | int | sim | eh a quantidade do produto e deve ser maior que zero. 
| descricao | texto | nao | uma breve descricao do produto com o no maximo 255 caracteres

**Exemplo de corpo do request**

```js
{
    "valor" : 100.00,
    "categoria_id" : 1,
    "conta_id" : 1,
    "data_cadastro" : "2023-01-27"
    "data_validade" : "2023-10-21"
}
```

**Codigos de resposta**

| codigo | descricao
|-|-
| 201 | produto cadastrada com sucesso
| 400 | erro na validacao dos dados da requisicao

---

## Detalhar Produto
`GET` /marketcontrol/api/produto/{id}

**Exemplo de corpo da resposta**

```js
{
    "valor" : 100.00,
    "categoria" : {
        "categoria_id" : 1,
        "nome" : "Sabao OMO"
        "data_cadastro" : "2023-10-7"
    }
}
```

**Codigos de resposta**

## Cadastrar Estabelecimento 
`POST` /marketcontrol/api/estabelecimento/cadastro

| campo | tipo | obrigatorio | descricao 
|-----|:----:|:-----------:|---------
| nome_estabelecimento | texto | sim | eh nome do estabelecimento. 
| categoria_id | int | sim | eh o id de uma categoria previamente cadastrada. 
| cnpj | numero | sim | eh o CNPJ do comercio.
| nome_proprietario | texto | sim | eh o nome do proprietario


**Exemplo de corpo do request**

```js
{
    "nome_estabelecimento" : "Mercadinho do Calvo Roger",
    "categoria_id" : 1,
    "cnpj" : "32.480.846/0001-62",
    "nome_proprietario" : "Roger"
}
```

**Codigos de resposta**

| codigo | descricao
|-|-
| 201 | estabelecimento cadastrado com sucesso
| 400 | erro na validacao dos dados da requisicao

---

## Analise de estoque
`GET` /marketcontrol/api/estoque/analise

| campo | tipo | obrigatorio | descricao 
|-------|:----:|:-----------:|--------- 
| categoria_id | int | sim | eh o id de uma categoria previamente cadastrada. 
| nome_produto | texto | sim | eh o nome do produto.
| valor_produto | numero | sim | eh o valor do produto.
| quantidade | numero | sim | eh a quantidade disponivel do produto.

**Exemplo de corpo do request**

```js
{
    "categoria_id" : 1,
    "nome_produto" : "Sabao Tixan",
    "valor_produto" : 10.00,
    "quantidade" : 10
}
```

**Codigos de resposta**

| codigo | descricao
|-|-
| 200 | produtos listados com sucesso
| 204 | nenhum conteudo
| 400 | erro na validacao dos dados da requisicao

## Cadastrar Responsavel 
`POST` /marketcontrol/api/cadastro/responsavel

| campo | tipo | obrigatorio | descricao 
|-----|:----:|:-----------:|---------
| nome | texto | sim | eh o nome do responsavel 
| cpf | int | sim | eh o cpf do responsavel 
| data_admissao | data | sim | eh a data de admissao 
| codigo_id | int | sim | eh o codigo de identificacao
| codigo_setor | int | sim | eh o codigo do setor de atuacao do responsavel. 

**Exemplo de corpo do request**

```js
{
    "nome" : "Jose Carlos Ferreira",
    "cpf" : 111.111.111-01,
    "data_admissao" : 2023-01-12,
    "codigo_id" : 120,
    "codigo_setor" : 240
}
```

**Codigos de resposta**

| codigo | descricao
|-|-
| 201 | responsavel cadastrado com sucesso
| 400 | erro na validacao dos dados da requisicao

---

## Perfil do Responsavel 
`GET` /marketcontrol/api/responsavel/{id}

| campo | tipo | obrigatorio | descricao 
|-------|:----:|:-----------:|---------
| nome | texto | sim | eh o nome do responsavel  
| codigo_id | int | sim | eh o codigo de identificacao
| codigo_setor | int | sim | eh o codigo do setor de atuacao do responsavel. 
| data_admissao | data | sim | eh a data de admissao 

**Exemplo de corpo do request**

```js
{
    "nome" : "Mario Kart De Assis",
    "cpf" : 111.777.345-06,
    "codigo_id" : 120,
    "codigo_setor" : 240
    "data_admissao" : 2023-01-12,
}
```

**Codigos de resposta**

| codigo | descricao
|-|-
| 200 | responsavel encontrado
| 400 | erro na validacao dos dados da requisicao

---

