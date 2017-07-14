

## Mydrive

Projeto de ES2016

## Documentação

Especificação e documentação do problema no [pdf](https://fenix.tecnico.ulisboa.pt/downloadFile/1970943312268892/es16p0.pdf) da página da disciplina.

* [First Sprint](https://fenix.tecnico.ulisboa.pt/downloadFile/563568428731757/es16p1.pdf)
* [Second Sprint](https://fenix.tecnico.ulisboa.pt/downloadFile/563568428736236/es16p2.pdf)
* [Third Sprint](https://fenix.tecnico.ulisboa.pt/downloadFile/845043405447749/es16p3.pdf)

## Configuração

Cada membro deve copiar a pasta `phonebook-V1/src/main/resources` para `mydrive/src/main/resources` e editar o ficheiro `fenix-framework-jvstm-ojb.properties` tendo em conta as configurações do ambiente de desenvolvimento de cada um.

## TODOs e FIXMEs



## Compilação
  
### 3º Sprint - Compilação, teste e execução:
`$ mvn clean install site exec:java -Dexec.args=drive.xml`

### Sprints anteriores
* Normal:
`$ mvn clean package exec:java`

* Testes:
`$ mvn clean test cobertura:cobertura site`



## Importação e exportação XML

* Importação

  Para importarem um `.xml` devem chamar, no método `MydriveApplication::main`, o método `ReadXMLFile::read`, passando como parâmetro o `path` onde se encontra o `.xml` que querem importar.
  
* Exportação

  Para exportarem um `.xml` devem chamar, no método `MydriveApplication::main`,o método `CreateXMLFile::create`, passando como parâmetro o `path` onde querem que seja gravado o `.xml` a exportar.
  Depois, no ficheiro `CreateXMLFile.java` devem alterar a forma como os valores são guardados no ficheiro `.xml`, i.e., devem aceder aos objectos que querem exportar e chamar os respetivos `getters` para passar os valores corretamente para o ficheiro.
  
## Paths

  Para garantir que a execução do programa corra sem erros, deverão ser alterados os caminhos:
  * `pathToCreatedXMLFile` e `pathToImportedXMLFile` no ficheiro `MydriveApplication.java`
  * `pathToInfoFolder` no ficheiro `SetupDomain.java`
