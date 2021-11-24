# Scrum

## Background

Scrum is Agile framework. In other words, it is a difined process with actionable steps to be Agile. Borrowing
terminology from OOP, Agile is absctract, while Scrum is a concrete implementation of Agile.

Scrum is a lightweight framework that helps people, teams and organizations generate value through adaptive solutions
for complex problems.

To understand Scrum, it is important to understand what a **sprint** is. Additionally, there are different high
level ideas to be aware of. There are the Scrum:

## Definition of Done

The definition of done is defined by the Scrum team. It specified what nedds to be fulfilled for a user story to be
considered complete.

To understand Scrum, it is important to understand what a **sprint** is. Additionally, there are three different high level ideas to be aware of. These are the Scrum

- Artifacts
- Roles
- Ceremonies

# Sprint

A Sprint is fundamentally the most important part of Scrum. It is where ideas are transformed into actual value in the form of **usable increments**.
Sprint characteristics:

- Should be fixed in length ranging from 1 to 4 weeks (2 weeks is typical)
- The product backlog is the input to a Sprint
- A protentially shippable increment is the output for a Sprint

# Scrum Artifacts

Scrum artifacts are used to help manage work and progress that needs to be made. There are three artifacts:

- Product backlog
  - An ordered list of everything that is currently part of the vision for the product
  - The product backlog is always changing and is never completed
  - The product owner is responsible for creating items and maintaining the product backlog
    - Job title of the product owner: PRODUCT MANAGER
- Sprint backlog
  - List of everything that the Scrum team decides to achieve for a particular sprint
  - Once planned and decided upon, only the development team can add more items to it
  - If the development team decides to remove an item from the Sprint backlog, they must negotiate this removal with the product owner
- Usable product increment
  - The actual product at the end of the Sprint
  - It must be "potentially releasable"
  - All completed items should meet the team's "definition of done"

# User Story

User stories are the items contained in the product backlog and sprint backlog. They are the features to be developed, written from the perspective of a user of the software.
Benefits of user stories:

- Keeps the focus on the user
- Stories enable collaboration
- Stories drive creative solutions
- Stories create momentum
  Reference: [Atlassian](https://www.atlassian.com/agile/project-management/user-stories)
  User story format:
- You should start with - "As a" - "I want to" - "So that I"
  Example: Adding Numbers (calculator app)
- As a user
- I want to be able to input two numbers and click the add button
- So that I can find the sum of those two numbers
  There are a couple of considerations when writing user stories:
- Acceptance criteria
- Definition of Done
- Story pointing
- Burndown charts (track progress quantitatively)

## Acceptance Criteria

Acceptance criteria help to define when the functionality of a user story has actually been implemented. From the perspective of a
user, the criteria is what they would perceive as "acceptable". Acceptance criteria typically follows the pattern of Given/When/Then
Example: Adding Numbers (calculator app)

- As a user
- I want to be able to input two numbers and click the add button
- So that I can find the sum of those two numbers

Acceptance criteria 1:

- Given that I am at the add numbers page of the website
- When I type in 10
- And I type in 25
- And click add
- Then I should see the result of 35

Acceptance criteria 2:

- Given that I am at the add numbers page of the website
- When I do not type in anything in the first input for a number
- But I do type in 20 for the second input for a number
- And I click add
- Then I should receive a message that says "No number found for the first input"
  Acceptance criteria 3:
- Given that I am at the add numbers page of the website
- When I do type in 20
- But I do not type in anything for the second input
- And I click add
- Then I should receive a message that says "No number found for the second input"
  Acceptance criteria 4:
- Given that I am at the add numbers page of the website
- When I do not type in anything for the first input
- And I do not type in anything for the second input
- And I click add
- Then I should receive a message that says "No number found for both the first and second input"

## Definition of Done

The definition of done is defined by the Scrum team. It specifies what needs to be fulfilled for a user story to be considered complete.
For example:

- Acceptance criteria met?
- Is the code peer reviewed?
- Do our unit tests for the code pass?
- Do our integration tests for the code pass?
- Do our automated E2E tests pass?
- Does the product owner approve of the feature that has been implemented?

## Story Pointing

Story pointing is all about estimating the amount of effort to complete a particular user story. This is accomplished by assigning a value to a user story. Rather than producing a concrete estimate (days, weeks), we use story points instead.
Story points could be

- Fibonacci numbers (1, 1, 2, 3, 5, 8, 13, 21, 34, 55, ...)
- T-shirt sizes (XS, S, M, L, XL)
- etc.

## Burndown chart

![burndown chart](./burndown-chart.PNG)

- Used to track progress of a sprint
- All of the user stories' story points are added up in the Sprint backlog
- As user stories are completed, the number of story points remaining can be visualized
- Should ideally go to 0 by the end of the Sprint

# Scrum Roles

1. Scrum Master
   - Responsible for facilitating proper Scrum practices within both the Scrum team and the wider organization
   - Help to clarify questions team members may have about Scrum
   - Assist in the removal of **impediments/blockers** in whatever way they can
   - Facilitate the **Scrum ceremonies**
2. Product Owner
   - Creates and orders the product backlog items
   - Explicitly communicates the product goal
   - Serves as the point of contact between the client and the Scrum team
3. Development Team
   - Software engineers + testers
   - Responsible for developing usable increments in each Sprint
   - Creating a plan for the Sprint
   - Adapting their plan towards the Sprint goal
   - Holding each other accountable

# Scrum Team

The Scrum roles describe the roles of different people involved in a small team known as a **Scrum team**.
A Scrum team consists of

- One Scrum master
- One product owner
- Many developers/testers
  Scrum teams should ideally
- Have 10 or fewer people
- Have no hierarchies
- Be cross-functional
- Be self-managing

# Scrum Ceremonies

The Scrum ceremonies are the "official" Scrum meetings. Ceremonies are designed to enable the transparency required for adapting and improving.
These ceremonies are of course not the only meetings that will realistically happen of course, but they should help to minimize meetings not defined within the Scrum framework. It is important to make the most of the Scrum ceremonies/meetings in order to create shared understanding and reduce ambiguity.
The ceremonies corresponding to a particular Sprint are as follows:

1. Sprint planning meeting
   - Lays out work for the Sprint
   - The entire Scrum team works together
     - The product owner facilitates discussion about the most important product backlog items and how they relate to the product goal
     - The developers plan work that is necessary to accomplish each user story
   - No more than 8 hours
   - No more than 8 hours
2. Daily Standup Meeting (Daily Scrum)
   - Occurs every day, usually at the start of the working day
   - Progress towards the Sprint goal and completion of the Sprint backlog is inspected
   - 15 minutes are allocated (no more than 15 minutes)
   - Each team member answers
     1. What did you do yesterday?
     2. What will you do today?
     3. Are there any impediments/blockers?
3. Sprint Review Meeting
   - Happens at the end of the Sprint
   - Inspect the outcome of the Sprint
   - Determine future adaptations to be made to the product
   - Present results of work to stakeholders
   - Progress towards the product goal is discussed
   - This is not the time to make critiques/criticize what was done
4. Sprint Retrospective
   - Occurs after the Sprint review meeting
   - The purpose is to see how to improve for the next Sprint
   - Identify the most useful changes to improve effectiveness
