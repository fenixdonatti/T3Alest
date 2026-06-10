# Trabalho 3 - ALEST

Implementação de estruturas de dados em Java incluindo Árvore Binária (Torneio Eliminatório) e Árvore Genérica (Menu de Aplicativo).

## Pré-requisitos

- **Java Development Kit (JDK) 8+** instalado em seu sistema
- **Variável de ambiente `JAVA_HOME`** configurada (opcional, mas recomendado)
- Terminal/Prompt de Comando

### Verificar instalação do Java

Execute o seguinte comando para verificar se o Java está instalado:

```bash
java -version
javac -version
```

## Como Compilar

### Opção 1: Compilar Todos os Arquivos (Recomendado)

Abra o terminal/prompt na pasta raiz do projeto (`T3Alest/`) e execute:

```bash
javac app/binaryTree/Node.java app/binaryTree/TournamentTree.java app/genericTree/GenericTree.java app/Main.java
```

### Opção 2: Compilar com Wildcard (Windows)

```bash
javac app\*.java app\binaryTree\*.java app\genericTree\*.java
```

### Opção 3: Compilar com Wildcard (Linux/macOS)

```bash
javac app/*.java app/binaryTree/*.java app/genericTree/*.java
```

## Como Executar

Após compilar com sucesso, execute o programa com:

```bash
java -cp app Main
```

> **Nota:** O `-cp app` indica ao Java onde encontrar as classes compiladas (arquivos `.class`).

## Estrutura do Projeto

```
T3Alest/
├── README.md
└── app/
    ├── Main.java              # Classe principal com menu interativo
    ├── binaryTree/
    │   ├── Node.java          # Classe de nó para árvore binária
    │   └── TournamentTree.java # Implementação da árvore binária
    └── genericTree/
        └── GenericTree.java   # Implementação da árvore genérica
```

## Usando o Programa

Ao executar, você verá um menu interativo com as seguintes opções:

1. **Modo 1**: Torneio Eliminatório (Árvore Binária)
2. **Modo 2**: Menu de Aplicativo (Árvore Genérica)
3. **Sair**: Encerrar o programa

Siga as instruções na tela para interagir com cada modo.