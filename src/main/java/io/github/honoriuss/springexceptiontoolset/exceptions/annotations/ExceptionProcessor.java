package io.github.honoriuss.springexceptiontoolset.exceptions.annotations;

import com.google.auto.service.AutoService;
import com.sun.source.tree.BlockTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.StatementTree;
import com.sun.source.util.TreePath;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.Trees;
import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.MethodSpec;
import org.springframework.javapoet.TypeName;
import org.springframework.javapoet.TypeSpec;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.*;
import javax.annotation.processing.*;
import javax.lang.model.*;
import javax.lang.model.element.*;
import javax.lang.model.util.*;
import com.sun.source.tree.*;
import com.sun.source.util.*;
import java.util.*;

import java.io.*;

import static java.util.stream.Collectors.joining;

@SupportedAnnotationTypes("io.github.honoriuss.springexceptiontoolset.exceptions.annotations.ExceptionAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(ExceptionProcessor.class)
public class ExceptionProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(ExceptionAnnotation.class)) {
            System.out.println("Halloooo");
            if (element.getKind() == ElementKind.METHOD) {
                ExecutableElement method = (ExecutableElement) element;
                try {
                    addPrintStatement(method);
                } catch (IOException e) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.toString(), element);
                }
            }
        }
        return true;
    }

    private void addPrintStatement(ExecutableElement method) throws IOException {
        Filer filer = processingEnv.getFiler();
        String className = method.getEnclosingElement().getSimpleName().toString();
        String methodName = method.getSimpleName().toString();

        // Öffne die Datei zum Schreiben
        FileObject fileObject = filer.createResource(StandardLocation.SOURCE_OUTPUT, "", className + "Generated");
        try (PrintWriter writer = new PrintWriter(fileObject.openWriter())) {
            // Schreibe den Originalcode
            String docComment = processingEnv.getElementUtils().getDocComment(method);
            if (docComment != null) {
                writer.println("/* Original comment */");
                writer.println(docComment);
            }
            writer.println(method);
            writer.println();

            // Schreibe den generierten Code
            writer.println("System.out.println(\"Method " + className + "." + methodName + "() is executed.\");");
        }
    }
}
