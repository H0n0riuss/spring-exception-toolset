package io.github.honoriuss.springexceptiontoolset.annotations;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("ExceptionAnnotation")
public class ExceptionProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(ExceptionAnnotation.class)) {
            if (element instanceof ExecutableElement) {
                ExecutableElement method = (ExecutableElement) element;
                String methodName = method.getSimpleName().toString();
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing method: " + methodName);
                // Hier kannst du benutzerdefinierte Codegenerierung durchführen
            }
        }
        return false;
    }
}
