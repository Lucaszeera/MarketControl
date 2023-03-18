# MarketControl
Sistema de controle de estoque de comércios.

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
    "categoria":{
        "categoria_id" : 1,
        "nome_categoria": "limpeza"
        }
    "data_cadastro" : "2023-01-27",   
    "data_validade" : "2023-10-21",
    "quantidade" : 15,
    "descricao" : 35,
    "produto_id" : 1
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
        "nome_categoria" : "limpeza"
    }
    "nome" : "Sabao OMO"
    "data_cadastro" : "2023-10-7"
    
}
```

**Codigos de resposta**
| codigo | descricao
|-|-
| 200 | dados do produto retornados no corpo da resposta
| 404 | id do produto não encontrado

---

## Cadastrar Estabelecimento 
`POST` /marketcontrol/api/estabelecimento

| campo | tipo | obrigatorio | descricao 
|-----|:----:|:-----------:|---------
| nome_estabelecimento | texto | sim | eh nome do estabelecimento. 
| categoria_id | int | sim | eh o id de uma categoria previamente cadastrada. 
| cnpj | numero | sim | eh o CNPJ do comercio.
| nome_proprietario | texto | sim | eh o nome do proprietario


**Exemplo de corpo do request**

```js
{
    "nome" : "Mercadinho do Calvo Roger",
    "id" : 2,
    "nome_categoria": 
    "bebidas e alimenticios",
    "cnpj" : "32.480.846/0001-62",
    "nomeProprietario" : "Roger",
    "cpfProprietario" : "010-202-330.04"
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

**Exemplo de corpo do request**

```js
{
    "id" : 1, 
    "nome_produto" : "Sabao Tixan",
    "categoria":{
        "categoria_id" : 2,
        "nome_categoria" : "enlatado"
        },
    "valor_produto" : 10.00,
    "quantidade" : 10
}
```

**Codigos de resposta**

| codigo | descricao
|-|-
| 200 | produtos retornados com sucesso
| 404 | não foram encontrados dados através dessa requisição

## Cadastrar Responsavel 
`POST` /marketcontrol/api/responsavel

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
    "id" : 120,
    "cpf" : 111.111.111-01,
    "data_admissao" : 2023-01-12,
    "setor" : {
        "codigo_setor" : 240
        "nome_setor" : "frutas"
        }
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

**Exemplo de corpo do request**

```js
{
    "nome" : "Mario Kart De Assis",    
    "id" : 120,
    "cpf" : 111.777.345-06,
    "data_admissao" : 2023-01-12,
    "setor" : {
        "codigo_setor" : 240,
        "nome_setor" : "frutas"
        }
}
```

**Codigos de resposta**

| codigo | descricao
|-|-
| 200 | responsavel encontrado
| 404 | id do responsavel não encontrado

---

