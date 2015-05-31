/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitorgreati.view.equipamentos;

import br.com.vitorgreati.model.Equipamento;
import br.com.vitorgreati.model.SessaoUsuario;
import br.com.vitorgreati.model.Usuario;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Vitor
 */
public class AvatarTools {
    
    public final static int AVATAR_USUARIO = 0;
    public final static int AVATAR_EQUIPAMENTO = 1;
    
    public static Image getAvatarTemporario(String path,int width, int height) throws IOException{
        //definindo de onde pegar
        File imagem = new File(path);
        //lendo a imagem
        BufferedImage bufferedImage = ImageIO.read(imagem);
        //retorna a imagem já redimensionada
        return bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);        
    }
    
    public static Image getAvatar(int tipoAvatar, int idObjeto,int width, int height) throws IOException{
        //definindo de onde pegar
        String pathSistema = new File(".").getCanonicalPath();
        String tipo = ""; // variável que guarda o tipo de avatar, se do usuário ou do equipamento, para definir o path
        String nomeAvatar = String.valueOf(idObjeto);
        switch(tipoAvatar){
            case AVATAR_USUARIO:
                tipo = "usuarios";
                break;
            case AVATAR_EQUIPAMENTO:
                tipo = "equipamentos";
                break;
        }
        File imagem = new File(pathSistema+"/img/"+tipo+"/"+nomeAvatar+".jpg");
        //lendo a imagem
        BufferedImage bufferedImage = ImageIO.read(imagem);
        //retorna a imagem já redimensionada
        return bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
    
    public static void salvarAvatar(int tipoAvatar, String pathOrigem, int idObjeto){
        //definir de onde vem a imagem (provavelmente de um JFileChooser
        File fileOrigem = new File(pathOrigem);
        //montar o path de destino
        try{
            String pathSistema = new File(".").getCanonicalPath();
            String tipo = "";
            String nomeAvatar = String.valueOf(idObjeto);
            switch(tipoAvatar){
                case AVATAR_USUARIO:
                    tipo = "usuarios";
                    break;
                case AVATAR_EQUIPAMENTO:
                    tipo = "equipamentos";
                    break;
            }
            //define arquivo de destino, de acordo com a variável tipo, que indica se é usuário ou equipamento
            File fileDestino = new File(pathSistema+"/img/"+tipo+"/"+nomeAvatar+".jpg");
            //copia o arquivo
            FileUtils.copyFile(fileOrigem, fileDestino);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public static void excluirAvatar(int tipoAvatar, int idObjeto){
        try{
            String pathSistema = new File(".").getCanonicalPath();
            String tipo = "";
            String nomeAvatar = String.valueOf(idObjeto);
            switch(tipoAvatar){
                case AVATAR_USUARIO:
                    tipo = "usuarios";
                    break;
                case AVATAR_EQUIPAMENTO:
                    tipo = "equipamentos";
                    break;
            }
            //define arquivo de destino, de acordo com a variável tipo, que indica se é usuário ou equipamento
            File file = new File(pathSistema+"/img/"+tipo+"/"+nomeAvatar+".jpg");
            //copia o arquivo
            FileUtils.deleteQuietly(file);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
}
