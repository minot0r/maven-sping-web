## Librairies utilisées pour le projet

### Spring, SpringBoot, SpringWeb
Spring est une plate-forme de développement open source utilisée pour créer des applications Java. Elle fournit une gamme complète de services et d'outils pour faciliter le développement d'une application, tels que le support de la programmation orientée objet et le support des langages de développement web.

### JPA
JPA (Java Persistence API) est un standard de persistence pour le développement Java. Il fournit une abstraction de la persistance de données et une base commune pour le développement d'applications sur plusieurs plates-formes.

### JPA for Spring
Spring Data JPA est une extension de Spring qui facilite le développement d'applications Java persistantes. Il fournit une couche d'abstraction pour le développeur pour créer des applications persistantes avec des bases de données relationnelles et non relationnelles.

### Hibernate
Hibernate est un framework ORM (Object Relational Mapping) pour le développement d'applications Java. Il fournit un mappage objet-relationnel pour la persistance des données et permet aux développeurs de créer des applications Java persistantes.

### H2
H2 est une base de données relationnelle open source légère et rapide. Elle prend en charge un grand nombre de fonctionnalités, notamment le support de SQL, le support de la persistance des données et le support des transactions.

### Thymeleaf
Thymeleaf est un moteur de modèles open source pour le développement web. Il fournit un ensemble de fonctionnalités qui facilitent le rendu des données sur des pages Web et la création d'interfaces utilisateur dynamiques.

### Spring DevTools
Spring DevTools est un ensemble d'outils open source qui simplifie le développement d'applications web basées sur Spring. Il fournit des fonctionnalités telles que le débogage, le profilage et le déploiement automatisé.

## FAQ

### 1. Avec quelle partie du code avons-nous paramétré l'url d'appel /greeting ?

La partie du code qui permet de paramètrer l'url d'appel est l'annotation @GetMapping :
```java
@Controller
public class HelloWorldController {

    @GetMapping("/greeting") // <- celle-ci
    public String greeting...
```

### 2. Avec quelle partie du code avons-nous choisi le fichier HTML à afficher ?
La fonction Greeting renvoie un string, ce string correspond au fichier dans le dossier templates qui sera utilisé.
C'est Thymeleaf qui se charge de charger le fichier.

### 3. Comment envoyons-nous le nom à qui nous disons bonjour avec le second lien ?
Le paramètre name est envoyé via une requête HTTP de type GET, on envoie ainsi cette donnée au serveur, qui sera interprétée par Spring Web pour
l'envoyée à la fonction greetings via l'annotation @RequestParam :

```java
public String greeting(
        @RequestParam(name="name", required = false, defaultValue = "World") String nameGET,
        ...
);
```

### 4. Relancez-votre application, retournez sur la console de H2. Avez-vous remarqué une différence ?
Oui, la table `ADDRESS` s'est créée.

### 5. Expliquez l'apparition de la nouvelle table en vous aidant de vos cours sur Hibernate, et de la dépendance Hibernate de Spring
Hibernate est un ORM (Object Relational Mapping) qui offre aux développeurs la possibilité d'accéder et de manipuler des données dans une base de données relationnelle. Cela se fait en traduisant les relations entre les données de la base de données en objets Java. Cela permet au développeur de travailler avec les objets Java au lieu de travailler directement avec des requêtes SQL.

### 6. Faites une requête de type SELECT sur la table Adress. Voyez-vous tout le contenu de data.sql ?
Oui car j'ai reconfiguré les properties de l'application tel quel :
```properties
spring.datasource.url=jdbc:h2:mem:testdb // Change default name of db
spring.datasource.driverClassName=org.h2.Driver // Driver used
spring.datasource.username=sa // Username used
spring.jpa.defer-datasource-initialization=true // Defer data initialization to load data.sql
```

### 7. L'annotation @Autowired
Quand Autowired est invoqué, il charge de faire le mapping entre les données en base et les classes Java, invoquant ainsi de nouveaux objets correspondants à chaque entrée en base (ligne).

### 8. Importation de bootstrap
Pour importer bootstrap, je télécharge le CSS minifié depuis le CDN de bootstrap, ensuite je le place dans le dossier static.
Je crée un nouveau fichier css.html qui sera un fragment utilisé dans chaque template, comme ça si mes importations changent je n'ai qu'a changer le fragment.
Puis je remplace le fragment.

### 9. Faut-il une clé API pour appeler MeteoConcept ?
Oui, il faut une clé d'API pour utiliser l'API REST de MeteoConcept.

### 10. Quelle URL appeler ?
L'URL à appeler est : https://api.meteo-concept.com/api/forecast/daily?token=[TOKEN_API]&latlng=[LAT,LON]
avec [TOKEN_API] qui est la clé d'API (disponible dans WeatherController#apiToken).
avec [LAT,LON] qui sont les coordonnées GPS du lieu visé.

### 11. Quelle méthode HTTP utiliser ?
Cet endpoint est un endpoint REST qui utilise la méthode HTTP GET, les paramètres sont passés dans l'URL, et la réponse est au format JSON avec le header `Accept: application/json`.
Les paramètres sont : `token` et `latlng`.

### 12. Comment passer les paramètres d'appels ?
Puisque l'endpoint utilise la méthode HTTP GET, les paramètres sont passés dans l'URL.
`?` marque le début des paramètres, et `&` sépare les paramètres.

### 13. Où est l'information dont j'ai besoin dans la réponse ?

#### 13.1 Pour afficher la température du lieu visé par les coordonnées GPS
Dans la réponse, il y a un tableau `forecast` qui contient les prévisions météo pour les 5 prochains jours, a des heures différentes.

#### 13.2 Pour afficher la prévision de météo du lieu visé par les coordonnées GPS
Chaque entrée du tableau `forecast` contient un champ `weather` qui contient la prévision météo pour l'heure visée.