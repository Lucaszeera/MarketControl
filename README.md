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

- [Cadastro de setor](#cadastrar-setor)
    - Cadastrar
    - Listar todos
    - Apagar
    - Alterar
    
- [Cadastro de funcionario](#cadastrar-funcionario)
    - Cadastrar
    - Listar todos
    - Apagar
    - Alterar

- [Analise de estoque](#analise-de-estoque)
    - Listar produtos
    
- [Perfil do funcionario](#perfil-do-funcionario)
    - Alterar
    - Mostrar os detalhes

--- 

## Cadastrar Produto 
`POST` /marketcontrol/api/produto

| campo | tipo | obrigatorio | descricao 
|-----|:----:|:-----------:|---------
| descricao | texto | sim | uma breve descricao do produto com o no maximo 50 caracteres
| valor | decimal | sim | é o valor do produto. deve ser maior que zero 
| dataCadastro | data | sim | é a data do cadastro
| dataValidade | data | sim | é a data de validade
| quantidade | int | sim | é a quantidade do produto e deve ser maior que zero
| estabelecimento | estab. | sim | é uma referência ao estabelecimento em que o produto está cadastrado. Deve ser informado o id do estabelecimento previamente cadastrado

**Exemplo de corpo do request**

```js
{
	"descricao": "maçã",
	"valor": 12.00,
	"data_cadastro": "2023-03-14",
	"data_validade": "2023-06-14",
	"quantidade": 201,
    "estabelecimento":{
		"id": 1
	}
}
```

**Codigos de resposta**

| codigo | descricao
|-|-
| 201 | produto cadastrado com sucesso
| 400 | erro na validacao dos dados da requisicao

---

## Detalhar Produto
`GET` /marketcontrol/api/produto/{id}

**Exemplo de corpo da resposta**

```js
{
    "id" : 1,
	"descricao": "maçã"
	"data_cadastro": "2023-03-14",
	"data_validade": "2023-06-14",
	"valor": 12.00,
	"quantidade": 201,
    "estabelecimento":{
		"id": 3,
        "nome": "Mercadinho indigena"
	}
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
| nome | texto | sim | eh nome do estabelecimento. 
| categoria | texto | sim | é o nome da categoria do estabelecimento, possui tipos de vendas
| cnpj | texto | sim | é o CNPJ do estabelecimento
| nomeProprietario | texto | sim | é o nome do proprietario
| cpfProprietario | texto | sim | é o cpf do proprietario


**Exemplo de corpo do request**

```js
{
    "nome" : "Mercadinho do Calvo Roger",
    "categoria": "bebidas e alimenticios",
    "cnpj" : "32.480.846/0001-62",
    "nome_proprietario" : "alobar",
    "cpf_proprietario" : "010-202-330.04"
}
```

**Codigos de resposta**

| codigo | descricao
|-|-
| 201 | estabelecimento cadastrado com sucesso
| 400 | erro na validacao dos dados da requisicao

---

## Cadastrar Setor
`POST` /marketcontrol/api/setor

| campo | tipo | obrigatorio | descricao 
|-----|:----:|:-----------:|---------
| nome | texto | sim | eh nome do setor. 
| descricao | texto | nao | detalhes adicionais sobre o setor
| estabelecimento | estab. | sim | é uma referência ao estabelecimento em que o produto está cadastrado. Deve ser informado o id do estabelecimento previamente cadastrado



**Exemplo de corpo do request**

```js
{
    "nome" : "bebidas alcoólicas",
    "descricao" : "Esse setor abrange todas as bebidas que contém de alguma taxa de álcool",
    "estabelecimento":{
        "id": 1
    }
}
```

**Codigos de resposta**

| codigo | descricao
|-|-
| 201 | setor cadastrado com sucesso
| 400 | erro na validacao dos dados da requisicao

---


## Cadastrar Funcionario 
`POST` /marketcontrol/api/funcionario

| campo | tipo | obrigatorio | descricao 
|-----|:----:|:-----------:|---------
| id | int | sim | eh o codigo de identificacao
| nome | texto | sim | eh o nome do funcionario 
| cpf | int | sim | eh o cpf do funcionario 
| dataAdmissao | data | sim | eh a data de admissao 
| setor | int | sim | é uma referência ao setor em que o funcionario está cadastrado. Deve ser informado o id do setor previamente cadastrado
| estabelecimento | estab. | sim | é uma referência ao estabelecimento em que o produto está cadastrado. Deve ser informado o id do estabelecimento previamente cadastrado

**Exemplo de corpo do request**

```js
{
    "nome" : "Mario Kart De Assis",    
    "cpf" : "111.777.345-06",
    "data_admissao" : "2023-01-12",
    "setor" : {
        "id" : 23,
        },
    "estabelecimento":{
        "id":1
    }
}
```

**Codigos de resposta**

| codigo | descricao
|-|-
| 201 | funcionario cadastrado com sucesso
| 400 | erro na validacao dos dados da requisicao

---

## Analise de estoque
`GET` /marketcontrol/api/produto

**Exemplo de corpo do request**

```js
[
	{
		"id": 1,
		"valor": 9.50,
		"data_cadastro": "2023-03-14T03:00:00.000+00:00",
		"data_validade": "2023-03-14T03:00:00.000+00:00",
		"quantidade": 101,
		"descricao": "Escova de massagear cabelo",
		"estabelecimento": {
			"id": 1,
			"nome": "Mercadinho do Calvo Roger",
			"cnpj": "32.480.846/0001-62",
			"nome_proprietario": "proprietario",
			"cpf_proprietario": "010-202-330.04"
		}
	},
	{
		"id": 2,
		"valor": 6.50,
		"data_cadastro": "2023-03-14T03:00:00.000+00:00",
		"data_validade": "2023-03-14T03:00:00.000+00:00",
		"quantidade": 51,
		"descricao": "Shampo",
		"estabelecimento": {
			"id": 1,
			"nome": "Mercadinho do Calvo Roger",
			"cnpj": "32.480.846/0001-62",
			"nome_proprietario": "proprietario",
			"cpf_proprietario": "010-202-330.04"
		}
	}
]
```

**Codigos de resposta**

| codigo | descricao
|-|-
| 200 | produtos retornados com sucesso
| 404 | não foram encontrados dados através dessa requisição

---

## Perfil do Funcionario 
`GET` /marketcontrol/api/funcionario/{id}

**Exemplo de corpo do resposta**

```js
{
    "id" : 1,
    "nome" : "Mario Kart De Assis",    
    "cpf" : "111.777.345-06",
    "data_admissao" : "2023-01-12",
    "setor" : {
        "id" : 23,
        "nome" : "frutas"
        },
    "estabelecimento":{
        "id":1,
        "nome": "mercadinho do calvo"
    }
}
```

**Codigos de resposta**

| codigo | descricao
|-|-
| 200 | funcionario encontrado
| 404 | id do funcionario não encontrado

---

