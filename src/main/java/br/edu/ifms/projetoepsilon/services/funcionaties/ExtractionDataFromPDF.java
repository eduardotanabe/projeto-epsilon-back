package br.edu.ifms.projetoepsilon.services.funcionaties;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import br.edu.ifms.projetoepsilon.DTO.VeiculoDTO;

public class ExtractionDataFromPDF {
	
	public static VeiculoDTO getDataFromDPF(File pdfFile) throws IOException {
	    PDDocument doc = PDDocument.load(pdfFile);
	    String text = new PDFTextStripper().getText(doc);
	    VeiculoDTO veiculoDTO = new VeiculoDTO();
	    String[] split = text.split(":");
	    veiculoDTO.setSequencialChassi(split[3].substring(1, 18));
	    veiculoDTO.setSequencialMotor(split[21].substring(1, 20));
	    veiculoDTO.setPlacaLicenca(split[4].substring(1, 8));
	    String[] marcaModelo = split[12].split("\\/");
	    veiculoDTO.setModelo(marcaModelo[1].substring(0, 18));
	    veiculoDTO.setAnoModelo(Integer.parseInt(split[16].substring(1, 5)));
	    veiculoDTO.setAnoFabricacao(Integer.parseInt(split[17].substring(1, 5)));
	    
	    System.out.println(veiculoDTO.getAnoFabricacao());
	    
	    doc.close();
	    
	    return veiculoDTO;
	}
		

}
