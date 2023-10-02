package io.github.honoriuss.springexceptiontoolset.exceptions.annotations;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("io.github.honoriuss.springexceptiontoolset.exceptions.annotations.ExceptionAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
public class ExceptionProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("Halloasd");

        for (Element element : roundEnv.getElementsAnnotatedWith(ExceptionAnnotation.class)) {
            //if (element instanceof ExecutableElement) {
            //ExecutableElement method = (ExecutableElement) element;
            String methodName = element.getSimpleName().toString();
            processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "Processing method: " + methodName);

            System.out.println("Die Methode '" + methodName + "' wird aufgerufen!");
            // Hier kannst du benutzerdefinierte Codegenerierung durchführen
            //}
        }
        return true;
    }
}
