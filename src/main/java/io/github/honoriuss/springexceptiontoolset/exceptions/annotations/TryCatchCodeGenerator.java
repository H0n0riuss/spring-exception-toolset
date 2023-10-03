package io.github.honoriuss.springexceptiontoolset.exceptions.annotations;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.util.ElementScanner8;
import java.io.IOException;
import java.io.Writer;

public class TryCatchCodeGenerator extends ElementScanner8<Void, Void> {

    private final Filer filer;
    private final ProcessingEnvironment processingEnv;

    public TryCatchCodeGenerator(ProcessingEnvironment processingEnv) {
        this.filer = processingEnv.getFiler();
        this.processingEnv = processingEnv;
    }

    @Override
    public Void visitExecutable(ExecutableElement e, Void aVoid) {
        // Füge hier den Try-Catch-Block ein
        addTryCatchBlock(e);
        return super.visitExecutable(e, aVoid);
    }

    private void addTryCatchBlock(ExecutableElement methodElement) {
        try {
            // Hole den Paketnamen der Originalklasse
            String packageName = processingEnv.getElementUtils().getPackageOf(methodElement).getQualifiedName().toString();

            // Hole den Writer für die Quelldatei im gleichen Paket
            Writer writer = filer.createSourceFile(packageName + "." + methodElement.getEnclosingElement().getSimpleName()).openWriter();

            // Schreibe den Try-Catch-Block in die Quelldatei
            writer.write("try {\n");
            // Hier den originalen Code der Methode
            writer.write("    // Originaler Code\n");
            writer.write("} catch (Exception e) {\n");
            writer.write("    e.printStackTrace(); // oder andere Fehlerbehandlung\n");
            writer.write("}\n");

            // Schließe den Writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
