# Equipment-Control-System
A Java Spring boot app for special equipment control system

Table of contents:

## [Routes](#Routes)

### [Authorization](#Authorization)

- [Sign up](#Sign up)
- [Sign in](#Sign in)

### [Users](#Users)

# Routes

## Authorization

### Sign in

- `POST /auth/sign-in`
- Login into account
- Returns user token

## Users

### Create user
- `POST /users/create`
- Creates user
- Returns user data with id

### Change user role