# 🤝 Guía de Contribución - Granja Inteligente

## 📋 Organización del Equipo

Este proyecto está diseñado para trabajo colaborativo usando **Git y GitHub**.

---

## 🌿 Estrategia de Ramas

### Rama Principal
- `master` o `main`: Código estable y funcional

### Ramas por Patrón/Módulo
Cada integrante puede trabajar en una rama específica:

```
feature/factory-method      - Implementación del Factory Method
feature/abstract-factory    - Implementación del Abstract Factory
feature/builder             - Implementación del Builder
feature/singleton           - Implementación del Singleton
feature/adapter             - Implementación del Adapter
feature/decorator           - Implementación del Decorator
feature/facade              - Implementación del Facade
feature/observer            - Implementación del Observer
feature/strategy            - Implementación del Strategy
feature/command             - Implementación del Command
feature/state               - Implementación del State
```

---

## 🔄 Flujo de Trabajo Git

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/granja-inteligente-patrones.git
cd granja-inteligente-patrones
```

### 2. Crear una Rama para tu Patrón

```bash
# Actualizar master primero
git checkout master
git pull origin master

# Crear y cambiar a tu rama
git checkout -b feature/tu-patron

# Por ejemplo:
git checkout -b feature/observer
```

### 3. Desarrollar tu Patrón

```bash
# Hacer cambios en los archivos
# ...

# Ver cambios
git status

# Agregar archivos modificados
git add src/main/java/com/grupo2/patterns/behavioral/observer/*

# Commit con mensaje descriptivo
git commit -m "feat: Implementar patrón Observer para sensores"
```

### 4. Subir tu Rama

```bash
# Primera vez
git push -u origin feature/tu-patron

# Subsecuentes
git push
```

### 5. Crear Pull Request

1. Ir a GitHub
2. Clic en "Compare & pull request"
3. Describir los cambios
4. Asignar revisores
5. Crear PR

### 6. Integrar Cambios

Después de aprobación:
```bash
# Cambiar a master
git checkout master

# Actualizar
git pull origin master

# Eliminar rama local (opcional)
git branch -d feature/tu-patron
```

---

## 📝 Convenciones de Commits

Usar **Conventional Commits**:

```
feat: Nueva funcionalidad
fix: Corrección de bug
docs: Documentación
refactor: Refactorización de código
test: Pruebas
style: Formato, punto y coma, etc.
```

### Ejemplos:

```bash
git commit -m "feat: Implementar Factory Method para animales"
git commit -m "fix: Corregir cálculo de alimento en EstrategiaInvierno"
git commit -m "docs: Agregar documentación al patrón Observer"
git commit -m "refactor: Simplificar GranjaFacade"
```

---

## 🏗️ Distribución de Trabajo Sugerida

### Módulo 1: Patrones Creacionales
**Integrante A**
- Factory Method
- Abstract Factory

**Integrante B**
- Builder
- Singleton

### Módulo 2: Patrones Estructurales
**Integrante C**
- Adapter
- Decorator

**Integrante D**
- Facade

### Módulo 3: Patrones Comportamentales
**Integrante E**
- Observer
- Strategy

**Integrante F**
- Command
- State

---

## 🔍 Checklist Antes de Pull Request

Antes de crear tu PR, verifica:

- [ ] El código compila sin errores
- [ ] Has probado tu implementación
- [ ] Código está comentado y documentado
- [ ] Sigues las convenciones de nomenclatura
- [ ] No hay conflictos con master
- [ ] Has actualizado documentación si es necesario
- [ ] Mensaje de commit es descriptivo

---

## 🐛 Resolución de Conflictos

Si hay conflictos al hacer merge:

```bash
# Actualizar tu rama con los cambios de master
git checkout master
git pull origin master

git checkout feature/tu-patron
git merge master

# Resolver conflictos manualmente en los archivos
# ...

# Después de resolver
git add .
git commit -m "fix: Resolver conflictos con master"
git push
```

---

## 📊 Revisión de Código

### Como Autor
- Describe claramente qué hace tu código
- Explica decisiones de diseño
- Responde comentarios constructivamente

### Como Revisor
- Verifica que sigue los patrones correctamente
- Sugiere mejoras de forma constructiva
- Aprueba si cumple estándares

---

## 🎯 Buenas Prácticas

### Commits Frecuentes
- Hacer commits pequeños y frecuentes
- Cada commit debe ser una unidad lógica

### Código Limpio
```java
// ✅ Bien: Nombres descriptivos
public class VacaLecheraFactory implements AbstractAnimalFactory {
    @Override
    public Animal crearAnimal(String id, double peso, int edad) {
        return new Vaca(id, "Holstein", peso, edad, 25.0);
    }
}

// ❌ Mal: Nombres crípticos
public class VLF implements AAF {
    public A ca(String i, double p, int e) {
        return new V(i, "H", p, e, 25.0);
    }
}
```

### Documentación JavaDoc
```java
/**
 * Patrón Observer: Sistema de alertas que recibe notificaciones
 * de los sensores cuando detectan condiciones anormales.
 * 
 * @author Equipo 2
 * @version 1.0
 */
public class SistemaAlerta implements Observer {
    // ...
}
```

---

## 🚀 Comandos Git Útiles

```bash
# Ver estado actual
git status

# Ver historial de commits
git log --oneline --graph

# Ver ramas
git branch -a

# Cambiar de rama
git checkout nombre-rama

# Crear y cambiar a nueva rama
git checkout -b nueva-rama

# Ver diferencias
git diff

# Deshacer cambios no commiteados
git checkout -- archivo.java

# Actualizar rama actual con cambios remotos
git pull

# Ver commits de un archivo específico
git log -- ruta/al/archivo.java

# Buscar en el código
git grep "AlimentadorGlobal"
```

---

## 📚 Recursos Adicionales

### Git
- [Pro Git Book](https://git-scm.com/book/es/v2)
- [GitHub Guides](https://guides.github.com/)
- [Atlassian Git Tutorial](https://www.atlassian.com/git/tutorials)

### Patrones de Diseño
- [Refactoring Guru](https://refactoring.guru/design-patterns)
- [SourceMaking](https://sourcemaking.com/design_patterns)

### Java
- [Java Documentation](https://docs.oracle.com/en/java/)
- [Effective Java](https://www.oreilly.com/library/view/effective-java/9780134686097/)

---

## 💬 Comunicación

### Canales Recomendados
- **Issues de GitHub**: Para bugs y mejoras
- **Pull Requests**: Para revisión de código
- **Discussions**: Para preguntas generales
- **WhatsApp/Slack**: Para coordinación rápida

### Plantilla de Issue

```markdown
**Título**: [PATRON] Breve descripción

**Descripción**:
Descripción detallada del problema o mejora

**Patrón Relacionado**:
Observer / Factory / etc.

**Archivos Afectados**:
- src/main/java/com/grupo2/...

**Pasos para Reproducir** (si es bug):
1. ...
2. ...

**Comportamiento Esperado**:
...

**Comportamiento Actual**:
...
```

---

## ✅ Criterios de Aceptación

Para que un PR sea aceptado debe:

1. ✅ Implementar correctamente el patrón de diseño
2. ✅ Compilar sin errores
3. ✅ Estar documentado con JavaDoc
4. ✅ Seguir las convenciones de código
5. ✅ No romper funcionalidad existente
6. ✅ Incluir ejemplo de uso (si aplica)
7. ✅ Ser revisado y aprobado por al menos 1 miembro

---

## 🏆 Mejores Prácticas de Equipo

### Daily Standup (Opcional)
Reunión corta diaria (15 min):
- ¿Qué hice ayer?
- ¿Qué haré hoy?
- ¿Tengo algún impedimento?

### Code Review
- Revisar PRs en máximo 24 horas
- Ser constructivo en los comentarios
- Aprobar solo si está listo para producción

### Integración Continua
- Mantener master siempre funcional
- Probar antes de hacer merge
- Resolver conflictos rápidamente

---

## 📅 Timeline Sugerido

### Semana 1: Patrones Creacionales
- Días 1-2: Factory Method y Abstract Factory
- Días 3-4: Builder y Singleton
- Día 5: Integración y pruebas

### Semana 2: Patrones Estructurales
- Días 1-2: Adapter y Decorator
- Días 3-4: Facade
- Día 5: Integración y pruebas

### Semana 3: Patrones Comportamentales
- Días 1-2: Observer y Strategy
- Días 3-4: Command y State
- Día 5: Integración y pruebas

### Semana 4: Integración Final
- Días 1-3: Integración completa y flujo automatizado
- Día 4: Documentación y README
- Día 5: Presentación y entrega

---

**Última actualización**: Octubre 2025
**Equipo**: Grupo 2 - Granja Inteligente
