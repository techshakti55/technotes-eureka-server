# TechNotes Git & GitHub Workflow

## 1. Purpose

This document defines the Git and GitHub workflow used by the TechNotes engineering team.

All developers must follow this workflow.

Main goals:

- Protect production code
- Avoid direct development on `main`
- Review code through Pull Requests
- Keep changes traceable through task IDs
- Reduce merge conflicts
- Maintain a company-style development process


# 2. Branch Structure

We use three primary branch levels.

```text
main
  │
  └── develop
        │
        ├── feature/TECH-101-eureka-server
        ├── feature/TECH-102-config-server
        ├── feature/TECH-103-api-gateway
        ├── fix/TECH-xxx-description
        └── docs/TECH-xxx-description
```

## main

`main` contains stable and production-approved code.

Developers must never work directly on `main`.

Code reaches `main` only after testing and approval.


## develop

`develop` is the integration branch.

Completed features are merged into `develop` through Pull Requests.

Example:

```text
feature branch
      ↓
Pull Request
      ↓
develop
```


## Feature Branch

Every task must have its own branch.

Example:

```text
feature/TECH-101-eureka-server
```

Branch format:

```text
feature/TECH-<issue-number>-<description>
```

Examples:

```text
feature/TECH-101-eureka-server
feature/TECH-102-config-server
feature/TECH-103-api-gateway
```


# 3. Clone Repository

A developer working on the project for the first time should clone the repository.

```bash
git clone https://github.com/techshakti55/technotes-eureka-server.git
```

Enter the project:

```bash
cd technotes-eureka-server
```

Check repository status:

```bash
git status
```

View available branches:

```bash
git branch -a
```


# 4. Start New Development Task

Never create a feature branch from an old feature branch.

Always start from the latest `develop`.

```bash
git switch develop
```

Download the latest code:

```bash
git pull origin develop
```

Create a feature branch:

```bash
git switch -c feature/TECH-101-eureka-server
```

Check branch:

```bash
git branch
```

Expected:

```text
main
develop
* feature/TECH-101-eureka-server
```


# 5. Work on Code

Developer performs the required coding changes.

Before staging files:

```bash
git status
```

See actual modifications:

```bash
git diff
```

This should be reviewed before committing.


# 6. Stage Changes

Stage all required files:

```bash
git add .
```

Check staged files:

```bash
git status
```

Review staged changes:

```bash
git diff --staged
```


# 7. Commit Changes

Commit messages must clearly explain the change.

Example:

```bash
git commit -m "feat: configure Eureka server"
```

Commit message types:

```text
feat:     New functionality
fix:      Bug fix
docs:     Documentation
test:     Test changes
refactor: Code restructuring
build:    Maven/dependency/build changes
chore:    Maintenance/configuration
```

Examples:

```bash
git commit -m "feat: add Eureka discovery server"

git commit -m "fix: disable Eureka self registration"

git commit -m "docs: add Git workflow"

git commit -m "build: update Spring Cloud version"
```


# 8. Push Feature Branch

First push:

```bash
git push -u origin feature/TECH-101-eureka-server
```

After upstream tracking is configured, future pushes can simply use:

```bash
git push
```


# 9. Create Pull Request

Open GitHub.

Create Pull Request:

```text
FROM

feature/TECH-101-eureka-server

TO

develop
```

Never create normal feature PR directly to `main`.

Correct:

```text
feature → develop
```

Incorrect:

```text
feature → main
```


# 10. Pull Request Description

PR should contain:

```text
Task:
TECH-101

Description:
Configured Eureka Discovery Server.

Changes:
- Added Eureka Server dependency
- Added @EnableEurekaServer
- Configured port 8761
- Disabled Eureka client registration
- Added application configuration

Testing:
Application started successfully.
Eureka dashboard verified.
```


# 11. Code Review

Another developer or team lead reviews the PR.

Reviewer checks:

```text
Code quality
Architecture
Naming
Configuration
Security
Tests
Dependency changes
Possible bugs
```

If changes are requested, developer continues on the same feature branch.

Example:

```bash
git add .
git commit -m "fix: address PR review comments"
git push
```

The existing Pull Request updates automatically.


# 12. Merge Feature into Develop

After approval:

```text
feature branch
      ↓
Pull Request Approved
      ↓
develop
```

Merge should normally happen through GitHub.

Do not manually copy files between branches.


# 13. After PR Merge

Developer updates local `develop`.

```bash
git switch develop
```

Then:

```bash
git pull origin develop
```

Delete completed local feature branch:

```bash
git branch -d feature/TECH-101-eureka-server
```

Optionally delete remote branch after merge:

```bash
git push origin --delete feature/TECH-101-eureka-server
```


# 14. Start Next Task

Always start from updated `develop`.

```bash
git switch develop
git pull origin develop
```

Then:

```bash
git switch -c feature/TECH-xxx-new-task
```


# 15. Daily Developer Workflow

Start of day:

```bash
git switch develop
git pull origin develop
```

Switch to your feature branch:

```bash
git switch feature/TECH-101-eureka-server
```

Bring latest `develop` changes into your branch when required:

```bash
git merge develop
```

Continue development.

Check changes:

```bash
git status
git diff
```

Commit:

```bash
git add .
git commit -m "feat: meaningful description"
```

Push:

```bash
git push
```


# 16. Merge Conflict

Suppose developer runs:

```bash
git merge develop
```

and Git reports a conflict.

Git may show:

```text
<<<<<<< HEAD

your feature code

=======

develop code

>>>>>>> develop
```

Developer must manually decide the correct final code.

After fixing conflict:

```bash
git add .
```

Then:

```bash
git commit -m "fix: resolve merge conflict"
```

Push:

```bash
git push
```


# 17. Useful Git Commands

Check current status:

```bash
git status
```

Check current branch:

```bash
git branch
```

Show local and remote branches:

```bash
git branch -a
```

Switch branch:

```bash
git switch develop
```

Create new branch:

```bash
git switch -c feature/TECH-xxx-description
```

See changes:

```bash
git diff
```

See staged changes:

```bash
git diff --staged
```

Stage files:

```bash
git add .
```

Commit:

```bash
git commit -m "feat: description"
```

Push:

```bash
git push
```

Pull latest changes:

```bash
git pull origin develop
```

Show commit history:

```bash
git log --oneline
```

Show branch graph:

```bash
git log --oneline --graph --decorate --all
```


# 18. Branch Naming Rules

Feature:

```text
feature/TECH-101-eureka-server
```

Bug fix:

```text
fix/TECH-120-registration-error
```

Documentation:

```text
docs/TECH-104-git-workflow
```

Refactoring:

```text
refactor/TECH-130-eureka-config
```


# 19. TechNotes Standard Workflow

```text
GitHub Issue
      ↓
TECH-XXX assigned to developer
      ↓
Developer updates develop
      ↓
Create feature branch
      ↓
Development
      ↓
git status
      ↓
git add
      ↓
git commit
      ↓
git push
      ↓
Pull Request
      ↓
Code Review
      ↓
Changes if required
      ↓
Approval
      ↓
Merge into develop
      ↓
Integration Testing
      ↓
Release PR
      ↓
main
      ↓
Deployment
```


# 20. Production Release

Normal development:

```text
feature
   ↓
develop
```

After multiple features are tested:

```text
develop
   ↓
Pull Request
   ↓
main
```

`main` should always represent stable release-ready code.


# 21. Important Rules

Never directly develop on:

```text
main
```

Avoid direct development on:

```text
develop
```

Always use task branches:

```text
feature/*
fix/*
docs/*
refactor/*
```

Never commit passwords, API keys, database passwords, AWS credentials or secrets.

Never commit:

```text
target/
.idea/
*.iml
.env
secret files
```

Always check:

```bash
git status
```

before:

```bash
git add .
```

Always review:

```bash
git diff
```

before committing.


# 22. TechNotes Example

Developer receives:

```text
TECH-101
Setup Eureka Server
```

Workflow:

```bash
git switch develop

git pull origin develop

git switch -c feature/TECH-101-eureka-server
```

Developer completes code.

Then:

```bash
git status

git diff

git add .

git commit -m "feat: configure Eureka discovery server"

git push -u origin feature/TECH-101-eureka-server
```

GitHub:

```text
Create Pull Request

feature/TECH-101-eureka-server
            ↓
         develop
```

Team lead reviews.

After approval:

```text
Merge PR
```

Developer updates local environment:

```bash
git switch develop

git pull origin develop

git branch -d feature/TECH-101-eureka-server
```


# 23. Definition of Done

A task is considered complete only when:

```text
Code completed
Build successful
Application tested
No unnecessary files committed
Commit message follows standard
Feature branch pushed
Pull Request created
Code reviewed
Requested changes completed
PR approved
PR merged into develop
Issue updated/closed
```

---

TechNotes Engineering Workflow