package io.github.honoriuss.springexceptiontoolset.exceptions.annotations;

import com.google.auto.service.AutoService;
import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.MethodSpec;
import org.springframework.javapoet.TypeName;
import org.springframework.javapoet.TypeSpec;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.util.Set;

import java.io.*;

import static java.util.stream.Collectors.joining;

@SupportedAnnotationTypes("io.github.honoriuss.springexceptiontoolset.exceptions.annotations.ExceptionAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
public class ExceptionProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(ExceptionAnnotation.class)) {
            if (element.getKind() == ElementKind.METHOD) {
                ExecutableElement methodElement = (ExecutableElement) element;
                TypeElement typeElement = (TypeElement) element.getEnclosingElement();
                String packageName = processingEnv.getElementUtils().getPackageOf(typeElement).getQualifiedName().toString();

                // Hier erstellst du den neuen Code mit JavaPoet
                String methodName = methodElement.getSimpleName().toString();
                String consoleOutput = String.format("System.out.println(\"Methode %s wird aufgerufen\");", methodName);

                MethodSpec existingMethod = MethodSpec.overriding(methodElement)
                        .addStatement("super.$L($L)", methodElement.getSimpleName(), methodParameters(methodElement))
                        .build();

                MethodSpec newMethod = MethodSpec.overriding(methodElement)
                        .addStatement(consoleOutput)
                        .addCode(existingMethod.code)
                        .returns(TypeName.get(methodElement.getReturnType()))
                        .build();

                TypeSpec newClass = TypeSpec.classBuilder(typeElement.getSimpleName().toString())
                        .addModifiers(Modifier.PUBLIC)
                        .addMethod(newMethod)
                        .build();

                JavaFile javaFile = JavaFile.builder(packageName, newClass)
                        .build();

                // Versuche den Code zu ersetzen
                try {
                    String fileName = packageName + "." + typeElement.getSimpleName() + ".java";
                    replaceFileContent(fileName, javaFile.toString());
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "Code für " + methodName + " generiert");
                } catch (IOException e) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Fehler beim Ersetzen des Codes: " + e.getMessage());
                }
            }
        }
        return true;
    }

    private void replaceFileContent(String fileName, String newContent) throws IOException {
        File file = new File(fileName);

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.print(newContent);
        }
    }

    private String methodParameters(ExecutableElement methodElement) {
        return methodElement.getParameters().stream()
                .map(parameter -> parameter.getSimpleName().toString())
                .collect(joining(", "));
    }

    private String getPackageName(TypeElement typeElement) {
        return processingEnv.getElementUtils().getPackageOf(typeElement).getQualifiedName().toString();
    }
}
