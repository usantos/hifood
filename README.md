# 🍽️ Restaurant App

Aplicativo Android desenvolvido com Jetpack Compose e arquitetura moderna, focado na listagem e busca de restaurantes com suporte a paginação, carregamento elegante e testes unitários.

---

## ✅ Funcionalidades

- 📦 **Arquitetura MVVM com Coroutines**  
  Estrutura limpa e desacoplada usando ViewModel, Repository e UseCase com suporte a operações assíncronas.

- 🎨 **Interface com Jetpack Compose**  
  Toda a interface do usuário construída com Compose, seguindo boas práticas de design moderno.

- 📄 **Listagem de Restaurantes com Paging 3**  
  Paginação eficiente e automática usando a biblioteca oficial do Jetpack.

- 🔍 **Busca de Restaurantes**  
  Campo de busca com atualização reativa da lista conforme o texto digitado.

- 🦴 **Carregamento com Skeleton**  
  Placeholder de carregamento enquanto os dados são buscados, oferecendo melhor experiência ao usuário.

- ⏱️ **Mock com Atraso na Requisição**  
  Simulação de tempo de resposta da API para testes e validação de estados de loading.

- 🖼️ **Renderização de Imagens com Coil**  
  Biblioteca leve e rápida para carregamento de imagens diretamente no Compose.

---

## 🧪 Testes

- ✅ **Teste unitário da `RestaurantViewModel`**
- 🧪 **Uso de [MockK](https://mockk.io/)** para simulação de dependências em testes

Execute os testes com:

```bash
./gradlew testDebugUnitTest
