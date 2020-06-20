package com.ifsc.tds.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import com.ifsc.tds.entity.Usuario;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

//https://github.com/itext/itextpdf/releases/tag/5.5.13.1
//https://www.vogella.com/tutorials/JavaPDF/article.html
public class ImpressoraPDF {

	private static String DESTINO_ARQUIVO = new File("").getAbsolutePath() + File.separator;
	@SuppressWarnings("unused")
	private static final Font FONTE_LETRA = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	@SuppressWarnings("unused")
	private static final Font FONTE_LETRA_NEGRITO = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static final Font FONTE_LETRA_TITULO_NEGRITO = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL);
	private static final Font FONTE_LETRA_PEQUENA = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
	public static String caminhoRelatorio = new String();

	public static <E> void criarArquivo(String nomeArquivo, String tituloRelatorio, String[] cabecalho, List<E> dados) {
		try {
			Document documento = new Document();
			PdfWriter.getInstance(documento, new FileOutputStream(ImpressoraPDF.DESTINO_ARQUIVO + nomeArquivo));
			documento.open();

			ImpressoraPDF.addInformacoes(documento);
			ImpressoraPDF.addTitulo(documento, tituloRelatorio);
			ImpressoraPDF.addConteudo(documento, cabecalho, dados);
			documento.close();
			ImpressoraPDF.caminhoRelatorio = ImpressoraPDF.DESTINO_ARQUIVO + nomeArquivo;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void addInformacoes(Document documento) {
		documento.addTitle("Listagem de usuários");
		documento.addSubject("Feito no RAU");
		documento.addKeywords("IFSC, PDF, iText");
		documento.addAuthor("IFSC TDS");
		documento.addCreator("IFSC TDS");
	}

	private static void addTitulo(Document documento, String tituloRelatorio) throws DocumentException {
		Paragraph paragrafoTitulo = new Paragraph();
		// Adiciona uma linha vazinha
		ImpressoraPDF.addLinhaVazia(paragrafoTitulo, 1);
		// Adiciona o título do relátorio
		paragrafoTitulo.add(new Paragraph(tituloRelatorio, ImpressoraPDF.FONTE_LETRA_TITULO_NEGRITO));
		paragrafoTitulo.add(new Paragraph("Gerado em " + new Date(), ImpressoraPDF.FONTE_LETRA_PEQUENA));
		ImpressoraPDF.addLinhaVazia(paragrafoTitulo, 1);
		documento.add(paragrafoTitulo);
	}

	private static <E> void addConteudo(Document documento, String[] cabecalho, List<E> dados)
			throws DocumentException {
		ImpressoraPDF.criarTabela(documento, cabecalho, dados);
	}

	private static <E> void criarTabela(Document documento, String[] cabecalho, List<E> dados)
			throws DocumentException {
		PdfPTable tabela = new PdfPTable(cabecalho.length);

		for (int i = 0; i < cabecalho.length; i++) {
			PdfPCell c1 = new PdfPCell(new Phrase(cabecalho[i]));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabela.addCell(c1);
		}
		tabela.setHeaderRows(1);
		
		for (E e : dados) {
			if (e instanceof Usuario) {
				tabela.addCell(((Usuario) e).getId().toString());
				tabela.addCell(((Usuario) e).getNome());
				tabela.addCell(((Usuario) e).getDataCadastroFormatada());
			}
		}

		documento.add(tabela);
	}

	private static void addLinhaVazia(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}
