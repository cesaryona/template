package com.ms.template;

import com.ms.template.controller.request.PersonRequestBody;
import com.ms.template.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class Seed implements CommandLineRunner {

    private final PersonService service;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 0; i++) {
            var person = generatePerson();
            log.info("{}", person);
            service.savePerson(person);
        }
    }


    public static PersonRequestBody generatePerson() {
        var faker = new Faker(new Locale("pt-BR"));
        var random = new Random();

        var name = faker.name().firstName();
        var lastName = faker.name().lastName();
        var document = gerarCPF();
        var email = faker.internet().emailAddress();


        return new PersonRequestBody(name, lastName, document, email);
    }

    private static String gerarCPF() {
        Random random = new Random();
        int[] numeros = new int[9];

        // Gera os nove primeiros dígitos do CPF
        for (int i = 0; i < 9; i++) {
            numeros[i] = random.nextInt(10);
        }

        // Calcula o primeiro dígito verificador
        int primeiroDigito = calcularDigitoVerificador(numeros, 10);

        // Adiciona o primeiro dígito verificador ao array
        int[] numerosComPrimeiroDV = new int[10];
        System.arraycopy(numeros, 0, numerosComPrimeiroDV, 0, 9);
        numerosComPrimeiroDV[9] = primeiroDigito;

        // Calcula o segundo dígito verificador
        int segundoDigito = calcularDigitoVerificador(numerosComPrimeiroDV, 11);

        // Monta o CPF com os dois dígitos verificadores
        StringBuilder cpf = new StringBuilder();
        for (int numero : numeros) {
            cpf.append(numero);
        }
        cpf.append(primeiroDigito);
        cpf.append(segundoDigito);

        return cpf.toString();
    }

    private static int calcularDigitoVerificador(int[] numeros, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < numeros.length; i++) {
            soma += numeros[i] * (pesoInicial - i);
        }

        int resto = soma % 11;
        if (resto < 2) {
            return 0;
        } else {
            return 11 - resto;
        }
    }


}
