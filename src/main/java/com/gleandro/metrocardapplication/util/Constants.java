package com.gleandro.metrocardapplication.util;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SUCCESS = "200";
    public static final String ERROR = "400";
    public static final String USER_PREFIX = "USR";
    public static final String TRANSFER_PREFIX = "TRF";
    public static final String ACCOUNT_PREFIX = "ACC";

    public static final String USER_LIST_SUCCESS = "Lista de usuarios obtenida con exito";
    public static final String USER_LIST_EMPTY = "No hay usuarios registrados";
    public static final String USER_CREATED = "Usuario creado con exito";
    public static final String USER_UPDATE = "Usuario actualizado con exito";
    public static final String USER_DUPLICATED = "El dni ya esta siendo usado";
    public static final String USER_NOT_FOUND = "Usuario no encontrado";
    public static final String USER_PASSWORD_INCORRECT = "Contraseña incorrecta";
    public static final String USER_SUCCESS_LOGIN = "Usuario logueado con exito";

    public static final String ACCOUNT_CREATED = "Cuenta creada con exito";
    public static final String ACCOUNT_UPDATE = "Cuenta actualizada con exito";
    public static final String ACCOUNT_NOT_FOUND = "Cuenta no encontrada";

    public static final String RECHARGE_ACCOUNT_CREATED = "Recarga realizada con exito";
    public static final String TRANSFER_CREATED = "Transferencia realizada con exito";
    public static final String TRANSFER_ERROR = "No se pudo realizar la transferencia";

    public static final String TIME_ZONE_DEFAULT = "America/Lima";

}
