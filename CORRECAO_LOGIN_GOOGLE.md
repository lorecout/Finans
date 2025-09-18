# 🔧 CORREÇÃO: Erro no Login com Google

## 📋 Problema Identificado

O erro no login com Google geralmente ocorre devido à **falta de configuração das chaves SHA-1/SHA-256** no Firebase Console.

## 🔑 Chaves SHA do Projeto

### Debug Keys (para desenvolvimento):
```
SHA-1:   D0:F1:0A:27:80:E1:40:92:95:6B:E4:9D:4F:62:66:E9:05:10:4C:DA
SHA-256: F8:DA:6E:CC:8F:57:82:8F:E6:33:33:10:A0:55:E6:F3:A9:7D:62:3A:F4:38:07:B8:FC:D8:43:BB:FD:1A:B2:EC
```

## 🚀 Como Corrigir

### Passo 1: Acesse o Firebase Console
1. Vá para: https://console.firebase.google.com/
2. Selecione o projeto **finanzen-sokph**

### Passo 2: Configurar SHA Keys
1. No menu lateral esquerdo, clique em **Configurações do projeto** (ícone de engrenagem)
2. Role para baixo até **Seus apps**
3. Clique no ícone do Android (Android app)
4. Na seção **Chaves de assinatura para upload**, clique em **Adicionar impressão digital**

### Passo 3: Adicionar SHA-1
1. Cole a chave SHA-1: `D0:F1:0A:27:80:E1:40:92:95:6B:E4:9D:4F:62:66:E9:05:10:4C:DA`
2. Clique em **Salvar**

### Passo 4: Adicionar SHA-256 (Opcional)
1. Clique novamente em **Adicionar impressão digital**
2. Cole a chave SHA-256: `F8:DA:6E:CC:8F:57:82:8F:E6:33:33:10:A0:55:E6:F3:A9:7D:62:3A:F4:38:07:B8:FC:D8:43:BB:FD:1A:B2:EC`
3. Clique em **Salvar**

### Passo 5: Baixar google-services.json Atualizado
1. Após adicionar as chaves, clique em **Baixar google-services.json**
2. Substitua o arquivo `android/app/google-services.json` no projeto
3. Execute `flutter clean` e `flutter pub get`

## 🔍 Verificação

### Teste no Emulador/Dispositivo
1. Execute o app: `flutter run`
2. Tente fazer login com Google
3. Verifique os logs no console para mensagens de erro

### Logs Esperados
Se funcionar corretamente, você deve ver:
```
✅ Firebase initialized successfully
🔄 Iniciando login com Google...
✅ Usuário Google selecionado: seu-email@gmail.com
✅ Credenciais obtidas com sucesso
✅ Login com Google realizado com sucesso
```

## ⚠️ Possíveis Problemas Adicionais

### 1. Google Play Services Desatualizado
**Sintomas**: Erro "Google Play Services is not available"
**Solução**: Atualizar Google Play Services no dispositivo/emulador

### 2. Conta Google não configurada
**Sintomas**: Login cancelado automaticamente
**Solução**: Verificar se há contas Google configuradas no dispositivo

### 3. Permissões insuficientes
**Sintomas**: Erro de rede ou conectividade
**Solução**: Verificar se o app tem permissões de internet

### 4. Firebase Auth desabilitado
**Sintomas**: Erro "operation-not-allowed"
**Solução**: No Firebase Console > Authentication > Sign-in method > Ativar Google

## 🧪 Teste de Produção

### Para Release (Google Play Store):
Quando for publicar o app, será necessário:

1. **Gerar keystore de produção**:
```bash
keytool -genkey -v -keystore release.keystore -alias alias_name -keyalg RSA -keysize 2048 -validity 10000
```

2. **Obter SHA-1 da keystore de produção**:
```bash
keytool -list -v -keystore release.keystore -alias alias_name
```

3. **Adicionar SHA-1 de produção no Firebase Console**

## 📞 Debug Avançado

Se o problema persistir, adicione logs detalhados no código:

```dart
// No método _loginComGoogle()
debugPrint('🔍 Google Sign-In Account: $googleUser');
debugPrint('🔍 Google Auth: idToken=${googleAuth.idToken != null}, accessToken=${googleAuth.accessToken != null}');
debugPrint('🔍 Firebase Auth current user: ${FirebaseAuth.instance.currentUser}');
```

## ✅ Status

Após seguir estes passos, o login com Google deve funcionar corretamente no modo debug.

**Data da correção**: 15 de setembro de 2025
**Status**: ✅ Pronto para teste