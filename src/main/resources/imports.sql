/*
 Puedes ejecutar esta consulta en la base de datos manualmente antes de iniciar la aplicación para evitar algún problema,
 y posteriormente, poder asignarle estas autoridades y roles a los usuarios.
 */
INSERT INTO authorities (authority)
VALUES ('MANAGE_USER'),
       ('MANAGE_VEHICLE'),
       ('MANAGE_INVENTORY'),
       ('AUDIT_INVENTORY'),
       ('MANAGE_PURCHASE'),
       ('APPROVE_PURCHASE'),
       ('PROCESS_REFUND'),
       ('MANAGE_SALE'),
       ('APPROVE_SALE'),
       ('PROCESS_RETURN'),
       ('MANAGE_REPORT'),
       ('MANAGE_MAINTENANCE'),
       ('BASIC_READ'),
       ('VIEW_DASHBOARD'),
       ('MANAGE_ROLES'),
       ('MANAGE_AUTHORITIES');

INSERT INTO roles (role)
VALUES ('ADMIN'),
       ('USER'),
       ('MANAGER'),
       ('PURCHASE'),
       ('SALE'),
       ('MECHANIC'),
       ('FINANCE'),
       ('IT_SUPPORT');
