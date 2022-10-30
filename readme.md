
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




## <span style="color:#66ff66"> Partie Cliente  SOAP PHP : :label:</span>

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



    
* <strong style="color: dark ; opacity: 0.80">Enfin nous tenons à remercier le seul et unique, notre professeur Mr YOUSFI Mohamed *Docteur & professeur à l'ENSET MEDIA* pour son soutien et son encouragement envers nous, aussi pour nous avoir donné cette opportunité d'améliorer nos compétences et de connaître les nouvelles technologies comme celles qui nous avons travaillé.

*voir également à propos* Mr [YOUSSFI Mohamed](https://www.linkedin.com/in/mohamed-youssfi-3ab0811b/)
</strong>

> Created by :[name=ELMAJNI KHAOULA]
[time=Mon,2022,10,20][color=#EF0101]
>*voir également à propos de moi* [ELMAJNI Khaoula](https://www.linkedin.com/in/khaoula-elmajni/)