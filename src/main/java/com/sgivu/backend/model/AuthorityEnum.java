package com.sgivu.backend.model;

public enum AuthorityEnum {

    // Autoridades relacionadas con usuarios
    MANAGE_USER,

    // Autoridades relacionadas con vehículos
    MANAGE_VEHICLE,

    // Autoridades relacionadas con inventario
    MANAGE_INVENTORY,
    AUDIT_INVENTORY,

    // Autoridades relacionadas con compras
    MANAGE_PURCHASE,
    APPROVE_PURCHASE,
    PROCESS_REFUND,

    // Autoridades relacionadas con ventas
    MANAGE_SALE,
    APPROVE_SALE,
    PROCESS_RETURN,

    // Autoridades relacionadas con reportes
    MANAGE_REPORT,

    // Autoridades relacionadas con mantenimiento
    MANAGE_MAINTENANCE,

    // Autoridades adicionales
    BASIC_READ,
    VIEW_DASHBOARD,
    MANAGE_ROLES,
    MANAGE_AUTHORITIES,
}
