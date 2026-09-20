import java.io.*;
import java.nio.file.*;

public class Main {

    private static final String XELATEX = "texlive/bin/universal-darwin/xelatex";
    private static final String TEX_FILE = "sap586.tex";

    // Aux files to delete
    private static final String[] AUX_FILES = {
            "sap586.aux",
            "sap586.log",
            "sap586.out",
            "sap586.toc",
            "sap586.fls",
            "sap586.fdb_latexmk",
            "sap586.synctex.gz"
    };

    public static void main(String[] args) {
        try {
            compileLatex();
            cleanAuxFiles();
            System.out.println("PDF generated and aux files removed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void compileLatex() throws IOException, InterruptedException {

        ProcessBuilder pb = new ProcessBuilder(
                XELATEX,
                TEX_FILE
        );

        pb.inheritIO();  // show XeLaTeX output in console
        Process p = pb.start();
        p.waitFor();
    }

    private static void cleanAuxFiles() {
        for (String aux : AUX_FILES) {
            try {
                Files.deleteIfExists(Paths.get(aux));
                System.out.println("Deleted: " + aux);
            } catch (IOException ignored) {}
        }
    }
}
