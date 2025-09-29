# MyTrailer systems integration - OLA 3 & 4

### Made by

- Pelle Hald Vedsmand - cph-pv73@stud.ek.dk
- Lasse Hansen - cph-lh479@stud.ek.dk
- Nicolai Rosendahl - cph-nr135@stud.ek.dk

## Objective of assignment (OLA 3):

We are being tasked with modeling the messaging flow between services and suggest an overall architecture for the MyTrailer system, using the DDD approach.

## Definition of Done for the modeling process:
- A clear understanding of the business domain and its requirements through event storming.
- Identification of the core domain and bounded contexts.
- Domain model diagrams per bounded context, alongside glossaries defining the ubiquitous language.
- C4 diagrams illustrating the system's architecture at various levels.

## Event storming
Our event storming board can be found [here](https://miro.com/app/board/uXjVJFl0CZ0=/).

We started out by doing an event storming session to get a better understanding of the business domain and its requirements. We identified the core domain, subdomains, and bounded contexts. We also identified the main events, commands, and aggregates in the system.

The following post-its were used in the event storming session:

![post_its.png](documentation/eventstorming/post_its.png)

- Orange: Being the events happening in the system.
- Blue: Being the commands that trigger the events.
- Red: Indicating hot spots that might represent uncertainties or potential issues needing attention.
- Pink: Representing external systems or actors that interact with the system.
- Purple: Being company policies or rules that govern the system's behavior.
- Yellow: Being actors, usually users making an action.
- Dark green: Representing the aggregates, entities and value objects in the system.
- Light green: Representing the need for reading data.

![eventstorming_board.png](documentation/eventstorming/eventstorming_board.png)

We tried to put the post-its in a logical order, representing the timeline or flow of the events taking place in the system. We also identified 2 aggregates consisting of multiple entities and value objects. These aggregates later became the basis for our bounded contexts.

## Bounded contexts
Based on the event storming session, we settled on the following 2 bounded contexts:
1. Rental context - handles everything to do with rental of trailers, including availability, booking and status updates.
2. Billing context - handles everything to do with billing and payments, including invoicing and payment processing.

We created glossaries for each bounded context, defining the ubiquitous language used within each context.

[Glossary](documentation/bounded_contexts/MyTrailer%20glossaries.docx)

Domain diagrams was also created for each bounded context, fleshing out the aggregates, entities, value objects and their relations within each context.

#### Rental Context

![rental_context_domain.png](documentation/bounded_contexts/rental_context_domain.png)

#### Billing Context

![billing_context_domain.png](documentation/bounded_contexts/billing_context_domain.png)

## C4 diagrams

We created C4 diagrams to illustrate the system's architecture at various levels, and used it as a tool to discuss and refine our architecture.

#### Level 1 - System Context Diagram

![C1.png](documentation/system_architecture/C1.png)

The only external service we have identified is a payment gateway, which is used to process payments. This service is only interacted with from the Billing context. Although, it was discussed that since MyTrailer also has a web solution that system could be used for login and user management, but it was decided that this was out of scope for the current assignment.

#### Level 2 - Container Diagram

![C2.png](documentation/system_architecture/C2.png)

C2 shows the two bounded contexts as separate containers, each with their own database. The rental context is responsible for most of the users immediate needs, and is therefore the main entry point for the system. The billing context is only interacted with when a rental is created/completed and an invoice needs to be generated.

#### Level 3 - Component Diagram

![C3.png](documentation/system_architecture/C3.png)

The component diagram deep dives into the content of each container and shows the main components and their interactions. 

Within the rental context, we have the following components:
- Location controller - provides functionality for searching and filtering trailers based on location and availability.
- Order controller - handles the creation and management of rental orders.
- Return controller - manages the return process of rented trailers.
- A message broker - used for communication between the rental and billing contexts. In our case, we have chosen to use RabbitMQ as the message broker using a publish-subscribe pattern, as the billing context will be subscribed to events happening in the rental context.

Within the billing context, we have the following components:
- Billing controller - handles the creation and management of invoices.
- A billing facade - used to loosen the coupling between the billing controller and the payment service.

## Conclusion of modeling process

Through the event storming session, we were able to gain a better understanding of the business domain and its requirements. We identified the core domain and bounded contexts, and created domain model diagrams and glossaries for each context. We also created C4 diagrams to illustrate the system's architecture at various levels. This approach gave us a top down exploration of the system and its requirements, providing us a better foundation for making the right architectural decisions.