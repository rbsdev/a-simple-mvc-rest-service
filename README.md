a-simple-mvc-rest-service
=========================

Projeto REST/Jersey simples de exemplo, tem o claro objetivo de auxiliar os colegas a desenvolver aplicações orientadas à serviços de forma contemporânea.


* O projeto foi desenvolvido utilizando padrões de mercado como:
    - Maven
    - EJB
    - Junit
    - RESTful (Jersey)
    - Mockito

* O projeto foi desenvolvido utilizando padrões/técnicas como:

    - MVC
    - SOA
    - REST
    - TDD

* Se houverem dúvidas ou problemas:

1 - Caso você não tenha configurado um runtime para o glassfish, utilize imagem 1 neste zip.
2 - Caso você tenha o runtime, pode conferir nas imagens 1 e 2 como proceder corretamente.
3 - Você pode ver na imagem 4 que o build (leia-se configuração no pom.xml) esta correto, se tiver algum problema, favor executar (maven clean, maven install) em seu eclipse. Obs: Esse ja é um projeto eclipse com maven configurado, como foi proposto o uso do eclipse preferi não criá-lo como projeto maven genérico mas como projeto eclipse (.project especifico) com configurações de maven.
4 - Caso queira simplificar e abstrair servidores de aplicação, você pode abrir a suite de teste localizada em test/br.com.gruporbs.ExampleSuiteTest, clicar com botao direito sobre e Run As - Junit, na imagem 5 você poderá ver que todo o sistema possui testes e estão funcionais.
