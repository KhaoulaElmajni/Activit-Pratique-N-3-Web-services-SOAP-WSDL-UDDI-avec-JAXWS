
# <strong style="color:blue; opacity: 0.80">Web services SOAP, WSDL, UDDI avec JAXWS </strong>:mortar_board::computer: 
# <span style="color:green "> 1.Présentation de l'activité pratique</span>
 * <strong style="color:dark">Il s’agit d'un Web service SOAP qui permet de : 
    • Convertir un montant de l’auro en DH
    • Consulter un Compte
    • Consulter une Liste de comptes
</span>
## <span style="color:#66ff66"> Partie Serveur : :label:</span>

## Déploiemen du Web service avec un simple Serveur JaxWS
>	
>	serveur JaxWS:

```java=10
public class ServeurJWS {
    public static void main(String[] args) {
        Endpoint.publish("http://0.0.0.0:8085/",new BanqueService());
        System.out.println("Web Service déployé sur l'adresse http://0.0.0.0:8085/!");
    }
}

```

![](https://i.imgur.com/5x9kvrO.png)



> La classe BanqueService POJO

```java=10
//POJO Plain Old Java Object ==> simple classe java
@WebService(serviceName = "BanqueWS")
public class BanqueService {
    @WebMethod(operationName = "Convert")
    public double conversion(@WebParam(name = "montant") double mt){
        return mt*11.01;
    }

    @WebMethod
    public Compte getCompte(@WebParam(name = "code") int code){
        return new Compte(code,new Date(),Math.random()*9888);
    }

    @WebMethod
    public List<Compte> comptes(){
        return List.of(
        new Compte(1,new Date(),Math.random()*9888),
        new Compte(2,new Date(),Math.random()*9888),
        new Compte(3,new Date(),Math.random()*9888)

        );
    }
}

```


## Consultation et analyse du fichier WSDL avec un Browser HTTP

![](https://i.imgur.com/24X1LdG.png)

![](https://i.imgur.com/63odS7B.png)


## Test des opérations du web service avec un l'outil SoapUI

![](https://i.imgur.com/NPvWYLM.png)

![](https://i.imgur.com/3PCLNAr.png)

![](https://i.imgur.com/s604gah.png)

![](https://i.imgur.com/NNhIpKP.png)


![](https://i.imgur.com/iYprbjk.png)


> La classe Compte 

```java=10

@XmlRootElement(name = "compte")
@XmlAccessorType(XmlAccessType.FIELD)
public class Compte {
    private int code;
    @XmlTransient
    private Date dateCreation;
    private double solde;

    public Compte(int code, Date dateCreation, double solde) {
        this.code = code;
        this.dateCreation = dateCreation;
        this.solde = solde;
    }

    public Compte() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
}

```

## <span style="color:#66ff66"> Partie Cliente SOAP Java : :label:</span>

>	Le Client du Web service:

```java=10
public class ClientWS {
    public static void main(String[] args) {
        //middleware
        BanqueService stub = new BanqueWS().getBanqueServicePort();
        System.out.println(stub.convert(5600));
        Compte compte = stub.getCompte(5);
        System.out.println(compte.getCode()+" "+compte.getSolde());

        List<Compte> comptes = stub.comptes();
        comptes.forEach(cp->{
            System.out.println("*****************");
            System.out.println(cp.getCode()+" : "+cp.getSolde());
        });
    }
}


```

>Le proxy à la partie cliente

![](https://i.imgur.com/I1ZBBCy.png)

![](https://i.imgur.com/J2ZPb1K.png)

## <span style="color:#66ff66"> Partie Cliente  SOAP Dot Net : :label:</span>

    
![](https://i.imgur.com/RR5BgXo.png)
![](https://i.imgur.com/bwQFXMs.png)



## <span style="color:#66ff66"> Partie Cliente  SOAP PHP : :label:</span>

```php=1

<?php
$mt = 0;
if (isset($_POST['action'])) {
    $action = $_POST['action'];
    if ($action == "OK") {
        $mt = $_POST['montant'];
        $client = new SoapClient("http://localhost:8085/BanqueWS?wsdl");
        $param = new stdClass();
        $param->montant = $mt;
        $rep = $client->__soapCall("Convert", array($param));
        $res = $rep->return;
    } elseif ($action == "comptes") {
        $client = new SoapClient("http://localhost:8085/BanqueWS?wsdl");
        $cptes = $client->__soapCall("comptes", array());
    }
}
?>

<html>
<body>
    <form method="post" action="ClientSOAP.php">
        Montant :<input type="text" name="montant" value="<?php echo ($mt) ?>">
        <input type="submit" value="OK" name="action">
        <input type="submit" value="comptes" name="action">
    </form>
    Rsultat :
    <?php if (isset($res)) { ?>
       <?php echo ($res) ?> en EURO = <?php echo($res)?> en DH
    <?php } ?>
    <?php if (isset($cptes)) { ?>
        <table border="1" width="80%">
            <tr>
                <th>CODE</th>
                <th>SOLDE</th>
            </tr>
            <?php foreach ($cptes->return as $cp) { ?>
                <tr>
                    <td><?php echo ($cp->code) ?></td>
                    <td><?php echo ($cp->solde) ?></td>
                </tr>
            <?php } ?>
        </table>
    <?php } ?>
</body>

</html>
```

![](https://i.imgur.com/GrmeiXx.png)

![](https://i.imgur.com/vxJOJGz.png)


## <span style="color:#66ff66">Déploiement du Web Service dans un Projet Spring Boot : :label:</span>



# <span style="color:green">3.Les Technologies utilisées</span>
 #### <span style="color:#0036ad"> 1.Java</span>
 * <strong style="color:dark">* <strong style="color:dark">Java est le langage de choix pour créer des applications à l'aide de code managé qui peut s'exécuter sur des appareils mobiles.

*voir également à propos* [JAVA](https://www.java.com/fr/):link: 


 #### <span style="color:#0036ad"> 2.JaxWS</span>
 * <strong style="color:dark">JAX-WS (Java API for XML Web Services) Est un ensemble d'interfaces (API) De langage de programmation Java dédié au développement de services web. L'ensemble fait partie de la plate-forme Java EE.
    

*voir également à propos de [JaxWS](https://boowiki.info/art/plate-forme-java/jax-ws.html) :link: 


 
## <span style="color:green ">2. Architecture du Web Service</span>
    
![](https://i.imgur.com/ivnUgx9.png)


  ---




 ## <strong style="color: green; opacity: 0.80" >3.comment ça marche ?</strong>
    
![](https://i.imgur.com/EaL63Cq.png)
![](https://i.imgur.com/Cl1yMvi.png)


    
- [ ]     Le client demande au stub de faire appel à la méthode conversion(12)
- [ ] Le Stub se connecte au Skeleton et lui envoie une requête SOAP
- [ ] Le Skeleton fait appel à la méthode du web service
- [ ] Le web service retourne le résultat au Skeleton
- [ ] Le Skeleton envoie le résultat dans une la réponse SOAP au Stub
- [ ] Le Stub fournie lé résultat au client

   
    
 ## <strong style="color: green; opacity: 0.80" >4. Création d'un Micro-service Spring boot Multi Connecteurs SOAP, REST, GRAPHQL qui permet de gérer des comptes</strong>

* Connecteur REST
    
![](https://i.imgur.com/zPO0OJl.png)
    
![](https://i.imgur.com/Aa847U7.png)

* Connecteur GraphQL

![](https://i.imgur.com/UDid0lT.png)

![](https://i.imgur.com/3nP59iC.png)

![](https://i.imgur.com/GB4Gskq.png)
    
![](https://i.imgur.com/nW0zOgg.png)

![](https://i.imgur.com/XKHPsEo.png)

![](https://i.imgur.com/SEObAwK.png)


* Connecteur SOAP
 * <strong style="color:dark"> Je me suis basée sur le blog suivant : [Create a SOAP Web Service with Spring
](https://www.baeldung.com/spring-boot-soap-web-service)
    Schema XSD
```xml=1
<?xml version="1.0" encoding="UTF-8"?>
<!--Published by XML-WS Runtime (https://github.com/eclipse-ee4j/metro-jax-ws). Runtime's version is XML-WS Runtime 4.0.0 git-revision#129f787.-->
<xsd:schema targetNamespace="http://service.web.ws.jax.com/" xmlns:ns0="http://service.web.ws.jax.com/"
            xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    <xsd:complexType name="compte">
        <xsd:sequence>
            <xsd:element name="code" type="xsd:long" minOccurs="0"/>
            <xsd:element name="montant" type="xsd:double"/>
        </xsd:sequence>
    </xsd:complexType>
    <xsd:complexType name="compteList"/>
    <xsd:complexType name="compteListResponse">
        <xsd:sequence>
            <xsd:element name="return" type="ns0:compte" minOccurs="0" maxOccurs="unbounded"/>
        </xsd:sequence>
    </xsd:complexType>
    <xsd:complexType name="Convert">
        <xsd:sequence>
            <xsd:element name="montant" type="xsd:double"/>
        </xsd:sequence>
    </xsd:complexType>
    <xsd:complexType name="ConvertResponse">
        <xsd:sequence>
            <xsd:element name="return" type="xsd:double"/>
        </xsd:sequence>
    </xsd:complexType>
    <xsd:complexType name="getCompte">
        <xsd:sequence>
            <xsd:element name="code" type="xsd:long" minOccurs="0"/>
        </xsd:sequence>
    </xsd:complexType>
    <xsd:complexType name="getCompteResponse">
        <xsd:sequence>
            <xsd:element name="return" type="ns0:compte" minOccurs="0"/>
        </xsd:sequence>
    </xsd:complexType>
    <xsd:element name="compte" type="ns0:compte"/>
    <xsd:element name="compteList" type="ns0:compteList"/>
    <xsd:element name="compteListResponse" type="ns0:compteListResponse"/>
    <xsd:element name="Convert" type="ns0:Convert"/>
    <xsd:element name="ConvertResponse" type="ns0:ConvertResponse"/>
    <xsd:element name="getCompte" type="ns0:getCompte"/>
    <xsd:element name="getCompteResponse" type="ns0:getCompteResponse"/>
</xsd:schema>

```

Plugin à ajouter à "pom.xml"
    
```xml=1
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>jaxb2-maven-plugin</artifactId>
    <version>1.6</version>
    <executions>
        <execution>
            <id>xjc</id>
            <goals>
                <goal>xjc</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <schemaDirectory>${project.basedir}/src/main/resources/</schemaDirectory>
        <outputDirectory>${project.basedir}/src/main/java</outputDirectory>
        <clearOutputDir>false</clearOutputDir>
    </configuration>
    <dependencies>
	<dependency>
		<groupId>javax.activation</groupId>
		<artifactId>activation</artifactId>
		<version>1.1.1</version>
	</dependency>
    </dependencies>
</plugin>

```

Compte Web Service Endpoint
    
```java=10
@Endpoint
public class CompteEndpoint {

    private static final String NAMESPACE_URI = "http://service.web.ws.jax.com/";

    private CompteRepository countryRepository;

    @Autowired
    public CompteEndpoint(CompteRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCompte")
    @ResponsePayload
    public JAXBElement<GetCompteResponse> getCompte(@RequestPayload JAXBElement<GetCompte> request) {
        GetCompteResponse response = new GetCompteResponse();
        response.setReturn(countryRepository.getCompte(request.getValue().getCode()));
        return new ObjectFactory().createGetCompteResponse(response);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "Convert")
    @ResponsePayload
    public JAXBElement<ConvertResponse> convert(@RequestPayload JAXBElement<Convert> request) {
        ConvertResponse response = new ConvertResponse();
        response.setReturn(countryRepository.convert(request.getValue().getMontant()));
        return new ObjectFactory().createConvertResponse(response);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "compteList")
    @ResponsePayload
    public JAXBElement<CompteListResponse> getCompteList(@RequestPayload JAXBElement<CompteList> request) {
        CompteListResponse response = new CompteListResponse();
        response.getReturn().addAll(countryRepository.getAllComptes());
        return new ObjectFactory().createCompteListResponse(response);
    }
}
```
    
Bean de la configuration du Endpoint
```java=1
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "comptes")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ComptesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://service.web.ws.jax.com");
        wsdl11Definition.setSchema(schema);
        return wsdl11Definition;
    }

    @Bean(name = "wsdl")
    public Wsdl11Definition defaultWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("wsdl.wsdl"));
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema schema() {
        return new SimpleXsdSchema(new ClassPathResource("schema.xsd"));
    }
}

```    

Le fichier WSDL généré
```xml=1
    <?xml version='1.0' encoding='UTF-8'?><!-- Published by XML-WS Runtime (https://github.com/eclipse-ee4j/metro-jax-ws). Runtime's version is XML-WS Runtime 4.0.0 git-revision#129f787. --><!-- Generated by XML-WS Runtime (https://github.com/eclipse-ee4j/metro-jax-ws). Runtime's version is XML-WS Runtime 4.0.0 git-revision#129f787. -->
<definitions xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"
             xmlns:wsp="http://www.w3.org/ns/ws-policy" xmlns:wsp1_2="http://schemas.xmlsoap.org/ws/2004/09/policy"
             xmlns:wsam="http://www.w3.org/2007/05/addressing/metadata"
             xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.web.ws.jax.com/"
             xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://schemas.xmlsoap.org/wsdl/"
             targetNamespace="http://service.web.ws.jax.com/" name="BanqueWS">
    <types>
        <xsd:schema>
            <xsd:import namespace="http://service.web.ws.jax.com/" schemaLocation="http://localhost:9191/?xsd=1"/>
        </xsd:schema>
    </types>
    <message name="Convert">
        <part name="parameters" element="tns:Convert"/>
    </message>
    <message name="ConvertResponse">
        <part name="parameters" element="tns:ConvertResponse"/>
    </message>
    <message name="getCompte">
        <part name="parameters" element="tns:getCompte"/>
    </message>
    <message name="getCompteResponse">
        <part name="parameters" element="tns:getCompteResponse"/>
    </message>
    <message name="compteList">
        <part name="parameters" element="tns:compteList"/>
    </message>
    <message name="compteListResponse">
        <part name="parameters" element="tns:compteListResponse"/>
    </message>
    <portType name="BanqueService">
        <operation name="Convert">
            <input wsam:Action="http://service.web.ws.jax.com/BanqueService/ConvertRequest" message="tns:Convert"/>
            <output wsam:Action="http://service.web.ws.jax.com/BanqueService/ConvertResponse"
                    message="tns:ConvertResponse"/>
        </operation>
        <operation name="getCompte">
            <input wsam:Action="http://service.web.ws.jax.com/BanqueService/getCompteRequest" message="tns:getCompte"/>
            <output wsam:Action="http://service.web.ws.jax.com/BanqueService/getCompteResponse"
                    message="tns:getCompteResponse"/>
        </operation>
        <operation name="compteList">
            <input wsam:Action="http://service.web.ws.jax.com/BanqueService/compteListRequest"
                   message="tns:compteList"/>
            <output wsam:Action="http://service.web.ws.jax.com/BanqueService/compteListResponse"
                    message="tns:compteListResponse"/>
        </operation>
    </portType>
    <binding name="BanqueServicePortBinding" type="tns:BanqueService">
        <soap:binding transport="http://schemas.xmlsoap.org/soap/http" style="document"/>
        <operation name="Convert">
            <soap:operation soapAction=""/>
            <input>
                <soap:body use="literal"/>
            </input>
            <output>
                <soap:body use="literal"/>
            </output>
        </operation>
        <operation name="getCompte">
            <soap:operation soapAction=""/>
            <input>
                <soap:body use="literal"/>
            </input>
            <output>
                <soap:body use="literal"/>
            </output>
        </operation>
        <operation name="compteList">
            <soap:operation soapAction=""/>
            <input>
                <soap:body use="literal"/>
            </input>
            <output>
                <soap:body use="literal"/>
            </output>
        </operation>
    </binding>
    <service name="BanqueWS">
        <port name="BanqueServicePort" binding="tns:BanqueServicePortBinding">
            <soap:address location="http://localhost:9191/"/>
        </port>
    </service>
</definitions>
```    
    
Tester les requetes SOAP
![](https://i.imgur.com/tEeH4s0.png)


![](https://i.imgur.com/7b0t9T0.png)

Connecteur Dot Net
    
```csharp=1
using System;
using System.IO;
using System.Net;
using System.Text;

namespace DotNetSoapClient
{
    internal class Program
    {
        public static void Main(string[] args)
        {
           GetAllComptesList();
           GetOneCompte(2);
           Convert(126);
        }

        public static void GetAllComptesList()
        {
            string getComptesList =
                @"<?xml version=""1.0"" encoding=""utf-8""?>
                <soapenv:Envelope xmlns:soapenv=""http://schemas.xmlsoap.org/soap/envelope/""
                xmlns:ser=""http://service.web.ws.jax.com/"">
                <soapenv:Header/>
                <soapenv:Body>
                <ser:compteList/>
                </soapenv:Body>
                </soapenv:Envelope>";
            
            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create("http://localhost:8080/ws");
            httpWebRequest.ContentType = "text/xml;charset=\"utf-8\"";
            httpWebRequest.Accept = "text/xml";
            httpWebRequest.Method = "POST";

            using (Stream stm = httpWebRequest.GetRequestStream())
            {
                using (StreamWriter streamWriter = new StreamWriter(stm))
                {
                    streamWriter.Write(getComptesList);
                }
            }

            WebResponse response = httpWebRequest.GetResponse();

            using (Stream stream = response.GetResponseStream())
            {
                if (stream != null)
                {
                    StreamReader reader = new StreamReader(stream, Encoding.UTF8);
                    String responseString = reader.ReadToEnd();
                    Console.WriteLine("The Response :\n");
                    Console.WriteLine(responseString);
                }

                Console.WriteLine("\n");
            } 
        }
        
        public static void GetOneCompte(int code)
        {
            string body=
                @"<soapenv:Envelope xmlns:soapenv=""http://schemas.xmlsoap.org/soap/envelope/"" xmlns:ser=""http://service.web.ws.jax.com/"">
                <soapenv:Header/>
                <soapenv:Body>
                <ser:getCompte>
                <code>"+code+@"</code>
                </ser:getCompte>
                </soapenv:Body>
                </soapenv:Envelope>";
            
            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create("http://localhost:8080/ws");
            httpWebRequest.ContentType = "text/xml;charset=\"utf-8\"";
            httpWebRequest.Accept = "text/xml";
            httpWebRequest.Method = "POST";

            using (Stream stm = httpWebRequest.GetRequestStream())
            {
                using (StreamWriter streamWriter = new StreamWriter(stm))
                {
                    streamWriter.Write(body);
                }
            }

            WebResponse response = httpWebRequest.GetResponse();

            using (Stream stream = response.GetResponseStream())
            {
                if (stream != null)
                {
                    StreamReader reader = new StreamReader(stream, Encoding.UTF8);
                    String responseString = reader.ReadToEnd();
                    Console.WriteLine("The Response :\n");
                    Console.WriteLine(responseString);
                }

                Console.WriteLine("\n");
            } 
        }
        
        
        public static void Convert(double montant)
        {
            string body=
                @"<soapenv:Envelope xmlns:soapenv=""http://schemas.xmlsoap.org/soap/envelope/"" xmlns:ser=""http://service.web.ws.jax.com/"">
                <soapenv:Header/>
                <soapenv:Body>
                <ser:Convert>
                <montant>"+montant+@"</montant>
                </ser:Convert>
                </soapenv:Body>
                </soapenv:Envelope>";
            
            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create("http://localhost:8080/ws");
            httpWebRequest.ContentType = "text/xml;charset=\"utf-8\"";
            httpWebRequest.Accept = "text/xml";
            httpWebRequest.Method = "POST";

            using (Stream stm = httpWebRequest.GetRequestStream())
            {
                using (StreamWriter streamWriter = new StreamWriter(stm))
                {
                    streamWriter.Write(body);
                }
            }

            WebResponse response = httpWebRequest.GetResponse();

            using (Stream stream = response.GetResponseStream())
            {
                if (stream != null)
                {
                    StreamReader reader = new StreamReader(stream, Encoding.UTF8);
                    String responseString = reader.ReadToEnd();
                    Console.WriteLine("The Response :\n");
                    Console.WriteLine(responseString);
                }

                Console.WriteLine("\n");
            } 
        }
    }
}
```
 
Test du connecteur c#
![](https://i.imgur.com/vqxQMVa.png)

    
* <strong style="color: dark ; opacity: 0.80">Enfin nous tenons à remercier le seul et unique, notre professeur Mr YOUSFI Mohamed *Docteur & professeur à l'ENSET MEDIA* pour son soutien et son encouragement envers nous, aussi pour nous avoir donné cette opportunité d'améliorer nos compétences et de connaître les nouvelles technologies comme celles qui nous avons travaillé.

*voir également à propos* Mr [YOUSSFI Mohamed](https://www.linkedin.com/in/mohamed-youssfi-3ab0811b/)
</strong>

> Created by :[name=ELMAJNI KHAOULA]
[time=Mon,2022,10,20][color=#EF0101]
>*voir également à propos de moi* [ELMAJNI Khaoula](https://www.linkedin.com/in/khaoula-elmajni/)