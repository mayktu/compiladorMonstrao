/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.compiladores.compiladorAlguma;

import java.io.FileWriter;
import java.io.IOException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author yasuo
 */
public class Principal {

    public static void main(String args[]) {
        try {
            CharStream cs = CharStreams.fromFileName(args[0]);
            RegrasLexicas lex = new RegrasLexicas(cs);
            FileWriter myWriter = new FileWriter(args[1]);

            Token t = null;
            while ((t = lex.nextToken()).getType() != Token.EOF) {
                if(RegrasLexicas.VOCABULARY.getDisplayName(t.getType()).equals("COMENTARIO_NAO_FECHADO")){
                    myWriter.write("Linha "+(lex.getLine()-1)+": comentario nao fechado\n");
                    break;
                }
                if(RegrasLexicas.VOCABULARY.getDisplayName(t.getType()).equals("CADEIA_NAO_FECHADA")){
                    myWriter.write("Linha "+(lex.getLine()-1) +": cadeia literal nao fechada\n");
                    break;
                }
                if(RegrasLexicas.VOCABULARY.getDisplayName(t.getType()).equals("ERRO")){
                    myWriter.write("Linha "+lex.getLine()+": "+t.getText()+" - simbolo nao identificado\n");
                    break;
                }
                myWriter.write("<'" + t.getText() + "'," + RegrasLexicas.VOCABULARY.getDisplayName(t.getType()) + ">\n");
                
            }
            myWriter.close();

        } catch (IOException ex) {
        }
    }
}
