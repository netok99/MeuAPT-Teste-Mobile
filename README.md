# MeuAPT-Teste-Mobile

Desenvolvido na linguagem Java, o app consome a api do Dribbble e lista os shots na primeira activity e mostra os detalhes do shot selecionado na segunda activity.

### **Funcionalidades** ###

- Teste de conexão com a internet nas activities.
- Paginação na tela de lista na primeira activity, com endless scroll / scroll infinito
- SwipeRefreshLayout(arraste a tela para baixo para atualizar o conteúdo) na primeira activities.
- Animação na transição das Activities nas duas activities.
- Cache após o request dos shots.
- O Client access token para acessar a api é guardado e acessado C/C++ Nativo NDK. Assim forneço a segurança necessário da informação.

### **Tratamento de rotção de tela** ###

Para demosntrar que existem várias formas de tratar a rotação de tela eu usei duas estratégias. A primeira activity activity eu guardo os dados no Bundle para evitar requests desnecessários quando ela rotacionar. Na segunda Activity eu informo no Manifest que quero que ela só funcione em modo portrait(em pé).

### **Bibliotecas** ###

Retrofit, Rxandroid, Dagger, Picasso, Espresso, JUnit, Okhttp
