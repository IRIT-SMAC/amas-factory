# amas-factory
[![Build Status](https://travis-ci.org/IRIT-SMAC/amas-factory.svg?branch=master)](https://travis-ci.org/IRIT-SMAC/amas-factory)
[![Coverage Status](https://coveralls.io/repos/IRIT-SMAC/amas-factory/badge.svg?branch=develop&service=github)](https://coveralls.io/github/IRIT-SMAC/amas-factory?branch=develop)

- [Github] (https://github.com/IRIT-SMAC/amas-factory)
- [Travis] (https://travis-ci.org/IRIT-SMAC/amas-factory)
- [Coveralls] (https://coveralls.io/github/IRIT-SMAC/amas-factory)
- [Démo] (https://github.com/IRIT-SMAC/demo-amas-factory)

Amas-factory a pour but de faciliter le déploiement de systèmes multi-agents (SMA), et plus particulièrement les systèmes multi-agents coopératifs (AMAS).

## Installation avec Maven

- Placez dans le pom.xml de votre projet la dépendance avec amas-factory.

	```xml
	<dependency>
		<groupId>fr.irit.smac</groupId>
		<artifactId>amas-factory</artifactId>
		<version>0.2</version>
	</dependency>
	```
- Rajoutez les lignes suivantes dans le pom.xml. Ainsi, les artefacts nécessaires seront recherchés (et trouvés) dans le dépôt Git [mvn-repo](https://github.com/IRIT-SMAC/mvn-repo).

	```xml
	<repositories>
		<repository>
			<id>IRIT-SMAC-agent-tooling-releases</id>
			<url>https://github.com/IRIT-SMAC/mvn-repo/raw/master/releases</url>
		</repository>
		<repository>
			<id>IRIT-SMAC-agent-tooling-snapshots</id>
			<url>https://github.com/IRIT-SMAC/mvn-repo/raw/master/snapshots</url>
		</repository>
	</repositories>
	```

## Description générale
Amas-factory se divise en trois entités distinctes principales : l'infrastructure, les services et les agents.

### Infrastructure
L'infrastructure permet d'accéder aux services et aux agents.

### Service
Dans le cadre d'amas-factory, un service s'occupe d'une préoccupation du système (par exemple la communication entre agents).
Actuellement, les services sont :

  - AgentHandlerService : contient la map des agents.
  - ExecutionService : gère l'exécution des agents.
  - MessagingService : gère la communication des agents.
  - LoggingService : gère le log (la trace) des agents.
  - HazelcastService : distribution des agents.

Les quatre premiers services proviennent de la bibliothèque [agent-tooling](https://github.com/IRIT-SMAC/agent-tooling).
Il est possible d'ajouter un service, d'en remplacer un ou de ne pas en utiliser un.

### Agent
Un agent dispose d'un "knowledge" (des connaissances) et d'un "skill" (des compétences) qui lui sont propres.

Un agent possède également une liste de features pouvant être utilisées par les autres agents également. Ces features possèdent chacune un knowledge et un skill. Une telle implantation assure la possibilité d'ajouter simplement des nouvelles fonctionnalités communes aux agents. Ainsi, il est possible d'ajouter une ou plusieurs features à cette liste, de remplacer une feature ou de ne pas en utiliser une déjà existante.
Actuellement, les features communes sont au nombre de deux : une feature basique et une feature sociale. 

#### Feature basique
Cette feature contient l'id de l'agent.

#### Feature sociale
Cette feature peut être utilisée pour un SMA social.  Elle  utilise le service de communication agent-messaging. 
Pour transmettre des données à un agent qu'il connaît, l'agent a deux possibilités : soit il définit la nature de ces données directement dans le message (dans le code), soit il utilise des ports.
Avec cette seconde méthode, l'agent expéditeur peut envoyer des données sur un port spécifique de l'agent qui interprètent ces dernières de la même façon. Il est également possible pour un agent de renseigner le port vers lequel il souhaite que son destinataire lui envoie des données.

## Initialisation du système

Deux solutions sont possibles pour initialiser le système :

- Par du code Java
- Par un fichier JSON : l'état initial de l'infrastructure, des services et des agents sont décrits dans ce fichier. Ces objets sont désérialisés à l'initialisation du système. Il est possible de créer ce fichier visuellement avec [amas-renderer](https://github.com/IRIT-SMAC/amas-renderer). La bibliothèque de sérialisation/désérialisation Jackson est utilisée. Avec celle-ci, il est possible d'utiliser des annotations pour notamment définir quels attributs sont pris en compte dans les processus de sérialisation et de désérialisation.
Si, quand vous implémentez une entité, par exemple le knowledge d'un agent, vous souhaitez que des champs de cette entité soit initialisés via le fichier JSON, il suffit de rajouter un @JsonProperty sur ce champ.

## Implémentation des différentes entités

Cette partie présente la façon d'implémenter les différentes entités d'un SMA avec amas-factory.

#### Implémentation d'un agent

Comme expliqué précédemment, un agent possède un knowledge et un skill qui lui sont propres. Il est donc nécessaire d'implémenter ces deux notions. La dernière étape est d'implémenter la classe de l'agent.

- Définir l'interface du knowledge propre à l'agent qui hérite de l'interface de base d'un knowledge.

	```java
	public interface IMyKnowledge extends IKnowledge
	```
- Implémenter cette interface. Cette classe doit également hériter de l'implémentation de base d'un knowledge.

	```java
	public class MyKnowledge extends Knowledge implements IMyKnowledge
	```
- Définir l'interface du skill propre à l'agent qui hérite de l'interface de base d'un skill.

	```java
	public interface IMySkill<K extends IMyKnowledge> extends ISkill<K>
	```

- Implémenter cette interface. Cette classe doit également hériter de l'implémentation de base d'un skill.

	```java
	public class MySkill<K extends IMyKnowledge> extends Skill<K> implements IMySkill<K>
	```

- Implémenter la classe de l'agent.

	```java
	public class MyAgent extends Agent<ICommonFeatures, IMyKnowledge, IMySkill<IMyKnowledge>> implements ITwoStepsAgent
	```	

	- IMyKnowledge correspond à l'interface du knowledge propre à un agent. 
	- IMySkill correspond à l'interface du skill utilisé.
	- ICommonFeatures correspond à l'interface des features utilisées. Si vous utilisez une liste de features personnalisées, remplacez cette interface par la vôtre.
	- TwoStepsAgent est la stratégie utilisée. Plus concrètement, elle définit les méthodes liées au cycle de vie d'un agent. Ainsi, en utilisant la stratégie ITwoStepsAgent, l'agent devra implémenter les méthodes perceive, qui concerne l'étape de perception, et decideAndAct, qui englobe les étapes de décision et d'action dans une seule méthode. Pour que le cycle d'un agent se compose d'une seule étape, l'interface IAgentStrategy peut être utilisée à la place de ITwoStepsAgent. Ces deux interfaces nécessitent dans tous les cas le service agent-scheduling.

- Implémenter les méthodes liées au cycle de vie de l'agent (et donc de la stratégie utilisée).
Dans notre exemple, il faut implémenter les méthodes perceive et decideAndAct.

#### Implémentation d'une nouvelle feature commune à plusieurs agents

Une feature permet d'ajouter des connaissances et des capacités supplémentaires utilisables par tous les agents si besoin. En d'autres termes, elle permet d'ajouter un comportement à un agent que d'autres peuvent également avoir. Pour cela, il faut implémenter le knowledge et le skill de la feature puis rajouter celle-ci à la liste des features communes.

- Définir les interfaces et les implémentations de ces interfaces du knowledge et du skill (de la même façon que dans la partie précédente).
- Rajouter cette feature à la liste des features communes. Pour cela, il faut définir une nouvelle interface de la liste des features (qui hérite donc de l'interface par défaut de la liste des features communes).

	```java
	public interface IMyFeatures extends ICommonFeatures {
	
	    public IFeature<IKnowledgeFeature, ISkillFeature<IKnowledgeFeature>> getMyFeature();
	    public void setMyFeature(IFeature<IKnowledgeFeature, ISkillFeature<IKnowledgeFeature>> myFeature);
	}
	```
- Implémenter cette nouvelle interface. Cette classe hérite de l'implémentation de base de la liste des features.

	```java
	public class MyFeatures extends Features implements IMyFeatures {
	    
	    @JsonProperty
	    public IFeature<IKnowledgeFeature, ISkillFeature<IKnowledgeFeature>> myFeature;
	    
	    @Override
	    public IFeature<IKnowledgeFeature, ISkillFeature<IKnowledgeFeature>> getMyFeature() {
	        return this.myFeature;
	    }
	    @Override
	    public void setMyFeature(IFeature<IKnowledgeFeature, ISkillFeature<IKnowledgeFeature>> myFeature) {
		    this.myFeature = myFeature;
		}
	}
	```
	  L'utilisation de l'annotation Jackson @JsonProperty permet d'intégrer l'attribut correspondant à la feature dans les processus de sérialisation et de désérialisation. Ceci est nécessaire pour prendre en compte cette feature quand on initialise le système via un un fichier JSON.

#### Implémentation d'un nouveau service

Pour ajouter un service, il suffit de créer la classe et son interface correspondante. Ensuite, il faut ajouter ce service à la liste des services.

- Définir une interface héritant de IService 

	```java
	public interface IMyService extends IService
	```
- Implémenter cette interface

	```java
	public class PlotService implements IPlotService
	```
- Rajouter ce service à la liste des services. Pour cela, il faut définir une nouvelle interface de la liste des services (qui hérite donc de l'interface par défaut de la liste des services).
	
	```java
	public interface IMyServices<A> extends IServices<A>
	```

- Implémenter cette nouvelle interface. Cette classe hérite de l'implémentation de base de la liste des services.

	```java
	public class MyServices<A extends IAgent<F,IKnowledge,ISkill<IKnowledge>>, F extends IFeatures> extends Services<A> implements IMyServices<A>
	```

#### Implémentation d'une infrastructure personnalisée

Si jamais vous souhaitez rajouter des paramètres ou des méthodes supplémentaires à l'infrastructure, vous pouvez en créer une nouvelle. Ceci permet d'ailleurs d'utiliser une toute nouvelle liste de services si besoin.

- Définir l'interface de cette infrastructure

	```java 
	public interface IMyInfrastructure<T> extends IInfrastructure<T>
	```
- Implémenter cette interface

	```java 
	public class MyInfrastructure<T extends IServices<A>, A> extends Infrastructure<T,A> implements IMyInfrastructure<T>
	```
	IServices correspond à l'interface de la liste des services utilisées. Si vous utilisez une liste de services personnalisée, renseignez son interface plutôt que celle par défaut (IServices).
	
#### Implémentation d'une factory personnalisée

Pour initialiser le système, on peut utiliser une factory. La factory de base est AmasFactory.
Il est possible d'implémenter une nouvelle factory. Pour cela, il suffit de faire hériter la nouvelle factory de la factory de base.

- Implémenter la factory
	
	```java
	public class MyFactory<T extends IServices<A>,A> extends AmasFactory<T,A>
	```
De même, si vous utilisez une liste de services personnalisée, renseignez son interface plutôt que celle par défaut (IServices).

- Ré-implémenter les méthodes createInfrastructure pour créer l'infrastructure

### Exécution du SMA (main)

Une fois toutes les implémentations souhaitées réalisées, il ne reste qu'à implémenter le main pour exécuter le SMA.

```java
AmasFactory<T, A> myFactory = new AmasFactory<>();

IInfrastructure<T> infra = (IInfrastructure<T>) myFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("config.json"));
```

"Config.json" correspond au fichier JSON utilisé pour initialiser le système.
Si vous utilisez une factory personnalisée ou une infrastructure personnalisée, veillez à remplacer les types utilisés. Par exemple, si vous avez remplacé la factory et l'infrastructure, le code sera donc :

```java
MyFactory<T,A> myFactory = new MyFactory();
        
IMyInfrastructure<T> infra = myFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("config.json"));
```

Avec agent-scheduling, l'instruction suivante permet d'exécuter les étapes d'un cycle de tous les agents.

```java
infra.getServices().getExecutionService().step();
```

Toujours avec agent-scheduling, il est également possible d'utiliser une interface graphique minimale pour manipuler les cycles du système avec cette instruction :
```java
infra.getServices().getExecutionService().displaySimpleGui();
```
