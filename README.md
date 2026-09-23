# Juke Mp3 Player — novo projeto

Projeto Android independente criado para receber o novo layout aprovado do JUKE.

- Package: `com.juke.mp3player`
- Kotlin + Jetpack Compose
- Java 17
- compileSdk / targetSdk 35
- minSdk 29
- Media3 incluído para integração posterior

## Etapa atual

Apenas a tela Biblioteca / Faixas está implementada. Os dados são fornecidos por
`LibraryRepository`, atualmente usando `MockLibraryRepository`, para permitir a
substituição futura pela biblioteca real sem acoplar o layout ao backend antigo.

O PNG oficial da biblioteca está em:

`app/src/main/res/drawable-nodpi/juke_layout_library.png`

Os nomes dos demais assets aprovados já estão reservados em
`ApprovedLayoutAssets` e serão adicionados somente quando as imagens oficiais
correspondentes forem fornecidas.
