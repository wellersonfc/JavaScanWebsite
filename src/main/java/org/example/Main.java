package org.example;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static int FiltroContador(String texto, String palavraChave){
        String str= texto;
        Pattern pattern = Pattern.compile("[ !@ #\\\\$%\\\\s\\\\^\\\\&*\\\\)\\\\(+=._,-]"+palavraChave+"[ !@ #\\\\$%\\\\^\\\\&*\\\\)\\\\(+=._,-]");
        Matcher matcher = pattern.matcher(str);

        int count = 0;
        while (matcher.find())
            count++;

        return count;
    }
    public static void main(String[] args) {

        try {
            Scanner coletarDados = new Scanner(System.in);
            String inputLine;

            String site, frase;

            System.out.printf("####################################################################\n");
            System.out.printf("############### ENCONTRE A PALAVRA NO SITE DESEJADO ################\n");
            System.out.printf("####################################################################\n\n");


            System.out.printf("Favor digitar o SITE desejado:\n");
            site = coletarDados.nextLine();


            System.out.printf("Favor digitar a PALAVRA desejada:\n");
            frase = coletarDados.nextLine();

            URL website = new URL(site);
            BufferedReader input = new BufferedReader(new InputStreamReader(website.openStream()));
            String resultWebTexto = "";

            while ((inputLine = input.readLine()) != null) {
                resultWebTexto += inputLine;
            }

            if(resultWebTexto != null || frase != null){

                System.out.println("*****RESULTADO*******\n\n");
                System.out.println(frase + " => se repete " + FiltroContador(resultWebTexto, frase) + " vezes\n ");


                String[] arrayPalavras = frase.split("\\s+");
                if(arrayPalavras.length > 1){
                    for (int i = 0; i < arrayPalavras.length; i++) {
                        arrayPalavras[i] = arrayPalavras[i].replaceAll("[^\\w]", "");
                        System.out.println(arrayPalavras[i] + " => se repete " + FiltroContador(resultWebTexto, arrayPalavras[i]) + " vezes\n ");
                    }
                }


            }else{
                System.out.println("Tivemos algum problema com o site ou a frase/palavra desejada :( !!!");
            }

            coletarDados.close();

        }catch (IOException e) {
            System.out.println(e);
        }
    }
}