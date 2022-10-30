
# <strong style="color:blue; opacity: 0.80">Web services SOAP, WSDL, UDDI avec JAXWS </strong>:mortar_board::computer: 
# <span style="color:green "> 1.Présentation de l'activité pratique</span>
 * <strong style="color:dark">Il s’agit d'un Web service SOAP qui permet de : 
    • Convertir un montant de l’auro en DH
    • Consulter un Compte
    • Consulter une Liste de comptes


5. Créer un Client SOAP Java</span>
## <span style="color:#66ff66"> Partie Serveur : :label:</span>

## Déploiemen du Web service avec un simple Serveur JaxWSLe
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

## Consultation et analyse du WSDL avec un Browser HTTP

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

## <span style="color:#66ff66"> Partie Client : :label:</span>

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

## <span style="color:#66ff66">  les fonctionnalités de l'application :label: </span>
L'application offre les fonctionnalités suivantes :


### 1. <span style="color:#001a33">.</span>
### 2. <span style="color:#001a33">.</span>
### 3. <span style="color:#001a33">.</span>
### 4. <span style="color:#001a33">.</span>

## <span style="color:#66ff66">La communication entre les micro-services :label: </span>
:

### L'interface de 'Open Feign' :

```java=10

public inetrface 
```


## <span style="color:#66ff66">Contraintes techniques :label: </span>
.

# <span style="color:green">3.Les Technologies utilisées</span>
 #### <span style="color:#0036ad"> 1.Java</span>
 * <strong style="color:dark">* <strong style="color:dark">Java est le langage de choix pour créer des applications à l'aide de code managé qui peut s'exécuter sur des appareils mobiles.

*voir également à propos* [JAVA](https://www.java.com/fr/):link: 


 #### <span style="color:#0036ad"> 3.Spring Data JPA</span>
 * <strong style="color:dark">Spring Data JPA, qui fait partie de la grande famille Spring Data, facilite la mise en œuvre de référentiels basés sur JPA. Ce module traite de la prise en charge améliorée des couches d'accès aux données basées sur JPA. Il facilite la création d'applications alimentées par Spring qui utilisent des technologies d'accès aux données.
    

*voir également à propos de [Spring Data JPA](https://spring.io/projects/spring-data-jpa) :link: 

#### <span style="color:#0036ad"> 4.MySQL</span>
 * <strong style="color:dark">est un système de gestion de base de données relationnelle (SGBDR) open source. Son nom est une combinaison de "My", le nom de la fille du co-fondateur Michael Widenius, et de "SQL", l'abréviation de Structured Query Language. Une base de données relationnelle organise les données en une ou plusieurs tables de données dans lesquelles les types de données peuvent être liés les uns aux autres ; ces relations aident à structurer les données. SQL est un langage utilisé par les programmeurs pour créer, modifier et extraire des données de la base de données relationnelle, ainsi que pour contrôler l'accès des utilisateurs à la base de données.
*voir également à propos* [MySQL](https://devdocs.io/css/) :link: 


 
## <span style="color:green ">4.Structure du projet</span>



 ## <span style="color:green ">5.CONCEPTION & ANALYSES</span>
 * ###### <strong style="color:red; opacity: 0.80">Diagramme de classe </strong>
> Diagramme de classe [color=#fd837b]

  ---




 ## <strong style="color: green; opacity: 0.80" >6.comment ça marche ?</strong>
    



    
* <strong style="color: dark ; opacity: 0.80">Enfin nous tenons à remercier le seul et unique, notre professeur Mr YOUSFI Mohamed *Docteur & professeur à l'ENSET MEDIA* pour son soutien et son encouragement envers nous, aussi pour nous avoir donné cette opportunité d'améliorer nos compétences et de connaître les nouvelles technologies comme celles qui nous avons travaillé.

*voir également à propos* Mr [YOUSSFI Mohamed](https://www.linkedin.com/in/mohamed-youssfi-3ab0811b/)
</strong>

> Created by :[name=ELMAJNI KHAOULA]
[time=Mon,2022,10,20][color=#EF0101]
>*voir également à propos de moi* [ELMAJNI Khaoula](https://www.linkedin.com/in/khaoula-elmajni/)