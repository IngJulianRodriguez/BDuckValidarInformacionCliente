package com.bankduck.ValidarInformacionCliente.Utils;

import java.util.Random;

public  class Generador {
    public static Long CodigoVerificacion(){
        Random random = new Random();

        long CodigoVerificacion = (long) (random.nextDouble() * 9000L) + 1000L;

        return CodigoVerificacion;

    }
}
