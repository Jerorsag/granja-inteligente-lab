# 💻 Ejemplos de Uso - Patrones Individuales

Esta guía muestra cómo usar cada patrón de forma independiente.

---

## 🏗️ PATRONES CREACIONALES

### 1. Factory Method

**Propósito**: Crear objetos sin especificar la clase exacta.

```java
// Uso básico
Animal vaca = AnimalFactoryImpl.crearAnimal("vaca", "V001", "Holstein", 600, 24);
Animal cerdo = AnimalFactoryImpl.crearAnimal("cerdo", "C001", "Duroc", 120, 6);

// Con parámetros personalizados
Animal vacaEspecial = AnimalFactoryImpl.crearAnimalPersonalizado(
    "vaca", "V002", "Jersey", 450, 18, 30.0  // 30 litros/día
);

// Imprimir información
vaca.mostrarInfo();
```

**Ventaja**: Fácil agregar nuevos tipos sin modificar código cliente.

---

### 2. Abstract Factory

**Propósito**: Crear familias de objetos relacionados.

```java
// Crear fábrica para vacas lecheras
AbstractAnimalFactory fabricaVacaLechera = new VacaLecheraFactory();

// Obtener familia completa de productos
Animal vacaLechera = fabricaVacaLechera.crearAnimal("V001", 600, 24);
String alimento = fabricaVacaLechera.crearAlimento();
String entorno = fabricaVacaLechera.crearEntorno();

System.out.println("Animal: " + vacaLechera.getTipo());
System.out.println("Alimento: " + alimento);
System.out.println("Entorno: " + entorno);

// Cambiar a otra familia de productos
AbstractAnimalFactory fabricaCerdoEngorde = new CerdoEngordeFactory();
Animal cerdo = fabricaCerdoEngorde.crearAnimal("C001", 120, 6);
// ... productos compatibles entre sí
```

**Ventaja**: Garantiza que los productos sean compatibles entre sí.

---

### 3. Builder

**Propósito**: Construcción paso a paso de objetos complejos.

```java
// Forma 1: Uso manual del builder
AnimalBuilder builder = new AnimalBuilder();

Animal animalComplejo = builder
    .setId("V001")
    .setTipo("vaca")
    .setRaza("Holstein")
    .setPeso(600.0)
    .setEdad(24)
    .setNivelActividad(95)
    .setHistorialMedico("Vacunación completa - 2024")
    .setParametroEspecifico(28.5)  // Producción de leche
    .build();

// Forma 2: Usar el director (construcciones predefinidas)
AnimalDirector director = new AnimalDirector(builder);

Animal vacaEstandar = director.construirVacaLechera("V002");
Animal cerdoEstandar = director.construirCerdoEngorde("C001");
Animal gallinaEstandar = director.construirGallinaPonedora("G001");

// Forma 3: Constructor con historial médico
Animal animalConHistorial = director.construirAnimalConHistorial(
    "V003", 
    "vaca", 
    "Jersey", 
    450, 
    18, 
    "Tratamiento preventivo - Enero 2025"
);

animalComplejo.mostrarInfo();
```

**Ventaja**: Construcción flexible sin constructores telescópicos.

---

### 4. Singleton

**Propósito**: Garantizar una única instancia global.

```java
// Obtener la única instancia (siempre devuelve la misma)
AlimentadorGlobal alimentador1 = AlimentadorGlobal.getInstancia();
AlimentadorGlobal alimentador2 = AlimentadorGlobal.getInstancia();

System.out.println(alimentador1 == alimentador2);  // true

// Dispensar alimento
alimentador1.dispensarAlimento("Corral-Cerdos", 50.0);
alimentador1.dispensarAlimento("Corral-Vacas", 150.0);

// Recargar inventario
alimentador1.recargarInventario(500.0);

// Ver estadísticas
alimentador1.mostrarEstadisticas();

// Desde cualquier parte del código, acceder al mismo estado
AlimentadorGlobal alimentador3 = AlimentadorGlobal.getInstancia();
System.out.println("Inventario: " + alimentador3.getInventarioTotal());
```

**Ventaja**: Control centralizado con un único punto de acceso.

---

## 🔧 PATRONES ESTRUCTURALES

### 5. Adapter

**Propósito**: Hacer que interfaces incompatibles trabajen juntas.

```java
// Sensor antiguo con interfaz incompatible
SensorLegacy sensorAntiguo = new SensorLegacy("OLD-001", "Almacén Principal");

// Adaptador que convierte a la nueva interfaz
SensorAdapter sensorAdaptado = new SensorAdapter(sensorAntiguo);

// Ahora funciona con la interfaz moderna
sensorAdaptado.setUmbrales(10.0, 90.0);
sensorAdaptado.tomarLectura();  // Usa realizarMedicion() internamente

// Se puede agregar a observadores como cualquier sensor moderno
SistemaAlerta alerta = new SistemaAlerta("Sistema Central");
sensorAdaptado.agregarObservador(alerta);
sensorAdaptado.tomarLectura();
```

**Ventaja**: Reutilización de código legacy sin modificarlo.

---

### 6. Decorator

**Propósito**: Agregar funcionalidades dinámicamente.

```java
// Animal base
Animal vaca = AnimalFactoryImpl.crearAnimal("vaca", "V001", "Holstein", 600, 24);

// Decorar con GPS
AnimalConGPS vacaConGPS = new AnimalConGPS(vaca);
vacaConGPS.actualizarPosicion(40.7128, -74.0060);
String ubicacion = vacaConGPS.rastrear();
System.out.println("Ubicación: " + ubicacion);

// Decorar con vacuna (puede decorar al decorador)
AnimalConVacuna vacaConGPSyVacuna = new AnimalConVacuna(vacaConGPS, "Fiebre Aftosa");

// Decorar con historial reproductivo
AnimalConHistorialReproductivo vacaCompleta = 
    new AnimalConHistorialReproductivo(vacaConGPSyVacuna);
vacaCompleta.registrarReproduccion(2);

// Mostrar toda la información (incluye decoraciones)
vacaCompleta.mostrarInfo();

// Los decoradores mantienen la interfaz original
vacaCompleta.alimentar(25.0);  // Funciona normalmente
```

**Ventaja**: Agregar funcionalidades sin modificar la clase base.

---

### 7. Facade

**Propósito**: Interfaz simplificada para subsistemas complejos.

```java
// Crear la fachada (punto de entrada único)
GranjaFacade granja = new GranjaFacade();

// Operaciones complejas simplificadas en un solo método
granja.crearCorral("Corral-Cerdos", "carne");

// Agregar animales es simple
Animal cerdo1 = granja.agregarAnimal("Corral-Cerdos", "cerdo", "C001", "Duroc", 120, 6);
Animal cerdo2 = granja.agregarAnimal("Corral-Cerdos", "cerdo", "C002", "Landrace", 115, 5);

// Cambiar estrategia de alimentación
EstrategiaAlimentacion estrategiaInvierno = new EstrategiaInvierno();
granja.cambiarEstrategiaAlimentacion(estrategiaInvierno);

// Alimentar corral completo (internamente usa Factory, Strategy, Command, Singleton)
granja.alimentarCorral("Corral-Cerdos");

// Monitorear sensores
granja.monitorearCorral("Corral-Cerdos");

// Activar riego
granja.activarRiego("Zona-Norte", 30);

// Ver estado completo
granja.mostrarEstadoGranja();
```

**Ventaja**: Simplifica operaciones complejas en métodos fáciles de usar.

---

## 🎯 PATRONES COMPORTAMENTALES

### 8. Observer

**Propósito**: Notificar automáticamente a múltiples objetos de cambios.

```java
// Crear el sujeto (sensor)
SensorNivelAlimento sensor = new SensorNivelAlimento("SA-001", "Corral-Cerdos");

// Crear observadores
SistemaAlerta alerta1 = new SistemaAlerta("Sistema Principal");
SistemaAlerta alerta2 = new SistemaAlerta("Sistema Backup");

// Registrar observadores
sensor.agregarObservador(alerta1);
sensor.agregarObservador(alerta2);

// Configurar umbrales
sensor.setUmbrales(20.0, 100.0);

// Cuando el sensor detecta algo, notifica automáticamente
sensor.setNivel(95.0);  // Normal, no notifica
sensor.setNivel(15.0);  // Bajo! Notifica a ambos observadores

// Los observadores reciben la notificación automáticamente
alerta1.mostrarHistorialAlertas();
```

**Ventaja**: Desacoplamiento entre sujetos y observadores.

---

### 9. Strategy

**Propósito**: Algoritmos intercambiables en tiempo de ejecución.

```java
Animal vaca = AnimalFactoryImpl.crearAnimal("vaca", "V001", "Holstein", 600, 24);

// Estrategia de Invierno
EstrategiaAlimentacion estrategiaInvierno = new EstrategiaInvierno();
double cantidadInvierno = estrategiaInvierno.calcularCantidadAlimento(vaca);
int frecuenciaInvierno = estrategiaInvierno.calcularFrecuencia();
System.out.println("Invierno: " + cantidadInvierno + " kg, " + frecuenciaInvierno + " veces/día");

// Cambiar a Estrategia de Verano
EstrategiaAlimentacion estrategiaVerano = new EstrategiaVerano();
double cantidadVerano = estrategiaVerano.calcularCantidadAlimento(vaca);
int frecuenciaVerano = estrategiaVerano.calcularFrecuencia();
System.out.println("Verano: " + cantidadVerano + " kg, " + frecuenciaVerano + " veces/día");

// Cambiar a Estrategia de Ahorro
EstrategiaAlimentacion estrategiaAhorro = new EstrategiaAhorro();
double cantidadAhorro = estrategiaAhorro.calcularCantidadAlimento(vaca);
int frecuenciaAhorro = estrategiaAhorro.calcularFrecuencia();
System.out.println("Ahorro: " + cantidadAhorro + " kg, " + frecuenciaAhorro + " veces/día");

// Uso con Context (clase que usa la estrategia)
class ContextoAlimentacion {
    private EstrategiaAlimentacion estrategia;
    
    public void setEstrategia(EstrategiaAlimentacion estrategia) {
        this.estrategia = estrategia;
    }
    
    public void alimentar(Animal animal) {
        double cantidad = estrategia.calcularCantidadAlimento(animal);
        animal.alimentar(cantidad);
    }
}

ContextoAlimentacion contexto = new ContextoAlimentacion();
contexto.setEstrategia(estrategiaInvierno);  // Cambiar algoritmo dinámicamente
contexto.alimentar(vaca);
```

**Ventaja**: Cambiar comportamiento sin modificar código.

---

### 10. Command

**Propósito**: Encapsular operaciones como objetos.

```java
// Crear el controlador (Invoker)
ControladorComandos controlador = new ControladorComandos();

// Crear comandos
Command dispensarAlimento = new DispensarAlimentoCommand("Corral-Cerdos", 50.0);
Command activarRiego = new EncenderRiegoCommand("Zona-Norte", 30);
Command registrarEvento = new RegistrarEventoCommand("Mantenimiento", "Corral-Vacas");

// Ejecutar comandos inmediatamente
controlador.ejecutarComando(dispensarAlimento);
controlador.ejecutarComando(activarRiego);

// Programar comandos para después
controlador.programarComando(registrarEvento);
Command otro = new DispensarAlimentoCommand("Corral-Gallinas", 20.0);
controlador.programarComando(otro);

// Ejecutar todos los pendientes
controlador.ejecutarComandosPendientes();

// Deshacer el último comando
controlador.deshacerUltimo();

// Ver historial
controlador.mostrarHistorial();
```

**Ventaja**: Historial de operaciones, deshacer/rehacer, programación.

---

### 11. State

**Propósito**: Cambiar comportamiento según el estado interno.

```java
Animal cerdo = AnimalFactoryImpl.crearAnimal("cerdo", "C001", "Duroc", 120, 6);

// Estado inicial: Sano
System.out.println("Estado actual: " + cerdo.getEstadoSalud().obtenerDescripcion());
cerdo.alimentar(3.0);  // Alimentación normal

// Cambiar a Enfermo
cerdo.cambiarEstado(new EstadoEnfermo());
System.out.println("Estado actual: " + cerdo.getEstadoSalud().obtenerDescripcion());
cerdo.alimentar(3.0);  // Alimentación reducida

// Aplicar tratamiento (automáticamente cambia a En Tratamiento)
cerdo.getEstadoSalud().aplicarTratamiento(cerdo);
System.out.println("Estado actual: " + cerdo.getEstadoSalud().obtenerDescripcion());

// Alimentar durante tratamiento (3 veces)
cerdo.alimentar(3.0);  // Día 1
cerdo.alimentar(3.0);  // Día 2
cerdo.alimentar(3.0);  // Día 3 - Automáticamente cambia a Sano

System.out.println("Estado final: " + cerdo.getEstadoSalud().obtenerDescripcion());
```

**Ventaja**: Elimina condicionales complejos, fácil agregar nuevos estados.

---

## 🔄 Ejemplo de Integración Completa

Combinando múltiples patrones:

```java
// 1. Crear sistema con Facade
GranjaFacade granja = new GranjaFacade();

// 2. Crear corral (instala sensores automáticamente)
granja.crearCorral("Corral-Mixto", "multiple");

// 3. Crear animales con diferentes métodos creacionales
// Factory Method
Animal cerdo = granja.agregarAnimal("Corral-Mixto", "cerdo", "C001", "Duroc", 120, 6);

// Builder
AnimalBuilder builder = new AnimalBuilder();
AnimalDirector director = new AnimalDirector(builder);
Animal vaca = director.construirVacaLechera("V001");
// (agregar manualmente a granja.getAnimalesCorral("Corral-Mixto"))

// 4. Decorar animal con GPS y Vacuna
Animal vacaDecorada = new AnimalConVacuna(
    new AnimalConGPS(vaca), 
    "Fiebre Aftosa"
);

// 5. Configurar estrategia de alimentación
granja.cambiarEstrategiaAlimentacion(new EstrategiaInvierno());

// 6. El sensor detecta nivel bajo (Observer)
SensorNivelAlimento sensor = granja.getSensorAlimento("Corral-Mixto");
sensor.setNivel(15.0);  // Dispara alerta

// 7. Alimentar (usa Command + Strategy + Singleton)
granja.alimentarCorral("Corral-Mixto");

// 8. Cambiar estado de salud (State)
cerdo.cambiarEstado(new EstadoEnfermo());
cerdo.getEstadoSalud().aplicarTratamiento(cerdo);

// 9. Ver estado completo
granja.mostrarEstadoGranja();
```

---

## 🎓 Consejos de Uso

1. **Factory**: Usa cuando necesites crear objetos pero no sepas el tipo exacto hasta runtime
2. **Singleton**: Úsalo con cuidado, solo para recursos verdaderamente globales
3. **Builder**: Ideal para objetos con muchos parámetros opcionales
4. **Adapter**: Perfecto para integrar código legacy o librerías de terceros
5. **Decorator**: Cuando necesites agregar funcionalidades sin herencia
6. **Facade**: Simplifica subsistemas complejos
7. **Observer**: Para notificaciones automáticas entre objetos
8. **Strategy**: Algoritmos intercambiables sin condicionales
9. **Command**: Encapsula operaciones para historial/deshacer
10. **State**: Cuando el comportamiento cambia según estado interno

---

**Guía de Ejemplos - Granja Inteligente**
