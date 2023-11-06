package br.com.tiagoiwamoto.adapter.out;

import br.com.tiagoiwamoto.adapter.out.dto.ImageDTO;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Future;

@ApplicationScoped
@Slf4j
public class ImageAndThumbAdapter {

    private final Double outputQuality = 0.7;
    private final Integer maxHeight = 200;


    public ImageDTO storeImage(FileUpload file, Path path){
        log.info("iniciando gravação da imagem ao path");
        String fileName = UUID.randomUUID().toString();
        String fileExtension = FilenameUtils.getExtension(file.fileName());
        String originalFileName = fileName.concat(".").concat(fileExtension);
        String thumbnail = fileName.concat("_th").concat(".").concat(fileExtension);
        try{
            if(!path.toFile().exists()){
                log.info("diretório não existe e será criado");
                path.toFile().mkdirs();
            }
            var fileInputStream = new FileInputStream(file.uploadedFile().toFile());
            Files.copy(fileInputStream, path.resolve(originalFileName));
            log.info("imagem foi transferida com sucesso");
//            var bfImage = ImageIO.read(file.uploadedFile().toFile());
//            BufferedImage img = Scalr.resize(bfImage, 250);
//            File outputfile = new File(path.toString().concat(File.separator).concat(thumbnail));
//            ImageIO.write(img, fileExtension, outputfile);
//            Files.copy(img, path.resolve(originalFileName));
//            Thumbnails.of(new File(path.toString().concat(File.separator).concat(originalFileName)))
//                    .height(maxHeight)
//                    .outputQuality(outputQuality)
//                    .toFile(new File(path.toString().concat(File.separator).concat(thumbnail)));
            StringBuilder sb = new StringBuilder();
            sb.append("./create_thumb.sh");
            sb.append(" ".concat(fileName));
            sb.append(" ".concat(fileExtension));
            sb.append(" ".concat("1"));

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(sb.toString());
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            log.info("resultado da execução do shellscript " + exitCode);
            log.info("thumbnail foi transferida com sucesso");
            return new ImageDTO(originalFileName, thumbnail);
        } catch (Exception e){
            log.error("não foi possível transferir a imagem");
            this.removeFiles(path);
            throw new RuntimeException(e);
        }
    }

//    BufferedImage simpleResizeImage(BufferedImage originalImage, int targetWidth) throws Exception {
//        return Scalr.resize(originalImage, targetWidth);
//    }

    public void removeFiles(Path path){
        try{
            log.info(String.format("iniciando remoção de arquivos para o path: %s", path));
            Files.walk(path)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
            log.info(String.format("arquivos foram removidos com sucesso do path %s", path));
        }catch (Exception e){
            log.error(String.format("falha ao remover arquivos para o path %s", path), e);
//            throw new RuntimeException(e);
        }
    }

    public ImageDTO validUpdateOfImage(Path path, FileUpload file, ImageDTO domain){
        log.info(String.format("validando se uma imagem sera armazenada ou substituida para o path: %s", path.toString()));
        ImageDTO imageDto;
        if(!Objects.isNull(file)){
            log.info(String.format("imagem será substituida por uma nova no path: %s", path));
            imageDto = this.replaceImage(file, path);
        }else{
            log.info("nenhuma nova imagem foi enviada");
            log.info(String.format("nenhuma nova imagem foi enviada para o path: %s", path));
            imageDto = new ImageDTO(domain.getPathOfImage(), domain.getPathOfThumb());
        }
        return imageDto;
    }

    public ImageDTO replaceImage(FileUpload file, Path path){
        this.removeFiles(path);
        return this.storeImage(file, path);
    }
}
