🍽️ Restaurant App
Aplicativo Android desenvolvido com foco em boas práticas modernas de desenvolvimento mobile. A aplicação lista restaurantes de forma paginada, permitindo busca, exibe esqueleto de carregamento (skeleton) e utiliza dados mockados para simulação de atrasos de rede.

📚 Tecnologias e Práticas Utilizadas
Arquitetura MVVM — Separação clara de responsabilidades entre View, ViewModel e Model.

Kotlin Coroutines — Gerenciamento de chamadas assíncronas de forma eficiente e legível.

Jetpack Compose — Construção de interfaces declarativas, modernas e reativas.

Paging 3 — Implementação de paginação para exibição eficiente da lista de restaurantes.

Busca de Restaurantes — Filtro e exibição de resultados conforme texto digitado.

Skeleton Loading — Exibição de layout temporário durante o carregamento dos dados.

Coil — Biblioteca moderna e eficiente para carregamento de imagens no Jetpack Compose.

Mock de Dados — Simulação de respostas com atraso para testes e desenvolvimento.

MockK — Framework de mocking leve e idiomático para testes unitários em Kotlin.

Teste Unitário da RestaurantViewModel — Garantia de que a lógica de negócios da ViewModel está funcionando corretamente.

🚀 Como rodar
Clone o repositório:

bash
Copiar
Editar
git clone https://github.com/seuusuario/restaurant-app.git
Abra no Android Studio Arctic Fox ou superior.

Compile e rode o projeto em um emulador ou dispositivo físico.

🧪 Testes
Os testes unitários podem ser executados com:

bash
Copiar
Editar
./gradlew testDebugUnitTest
Testes da RestaurantViewModel com o uso de MockK estão localizados em:

swift
Copiar
Editar
app/src/test/java/com/seupacote/viewmodel/RestaurantViewModelTest.kt
🖼️ Screenshots
(Adicione aqui prints da tela inicial, loading, busca e erro se desejar)
