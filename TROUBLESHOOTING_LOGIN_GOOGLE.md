# 🔐 Guia de Troubleshooting - Login Google

## 📋 Problemas Comuns e Soluções

### ❌ **Erro: "Login cancelado pelo usuário"**
**Causa:** Usuário fechou a tela de seleção de conta
**Solução:** Permitir que o usuário tente novamente

### ❌ **Erro: "Falha ao obter credenciais"**
**Causas possíveis:**
- Conexão com internet instável
- Conta Google com problemas
- Aplicativo não autorizado no Google Cloud Console

**Soluções:**
1. Verificar conexão com internet
2. Tentar com outra conta Google
3. Verificar configuração no Google Cloud Console

### ❌ **Erro: "Esta conta já existe com um método de login diferente"**
**Causa:** Usuário já tem conta com email/senha
**Solução:** Orientar usuário a fazer login com o método original

### ❌ **Erro: "Login com Google não está habilitado"**
**Causa:** Google Sign-In não está ativado no Firebase Console
**Solução:**
1. Acesse [Firebase Console](https://console.firebase.google.com)
2. Vá para "Authentication" → "Sign-in method"
3. Ative "Google"

### ❌ **Erro: "Credenciais inválidas"**
**Causas possíveis:**
- Chaves SHA incorretas
- Aplicativo não registrado corretamente
- Problemas com google-services.json

**Soluções:**
1. Verificar chaves SHA no Firebase Console
2. Baixar novo google-services.json
3. Limpar cache do app

---

## 🔧 Verificações Técnicas

### **1. Chaves SHA (Importante!)**
```bash
# Para debug (Android Studio)
keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android

# Para release (se aplicável)
keytool -list -v -keystore [caminho-para-keystore] -alias [alias]
```

### **2. google-services.json**
Verificar se contém:
- ✅ `project_id`: "finanzen-sokph"
- ✅ `package_name`: "com.lorecout.finans"
- ✅ `client_id` correto

### **3. AndroidManifest.xml**
Verificar permissões:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

### **4. Firebase Console**
Verificar:
- ✅ Google Sign-In está ativado
- ✅ SHA-1/SHA-256 estão cadastrados
- ✅ Package name está correto

---

## 🐛 Diagnóstico Rápido

### **Teste Básico:**
1. **Limpar dados do app** no dispositivo
2. **Reiniciar dispositivo**
3. **Testar com Wi-Fi e dados móveis**
4. **Testar com conta Google diferente**

### **Logs de Debug:**
Adicione ao código para debug:
```dart
debugPrint('Google Sign-In Debug: $variable');
```

### **Teste em Diferentes Cenários:**
- ✅ App recém-instalado
- ✅ App com dados anteriores
- ✅ Conta Google nova vs existente
- ✅ Conexão Wi-Fi vs Dados móveis

---

## 🚀 Soluções Avançadas

### **Se nada funcionar:**

1. **Recriar projeto Firebase:**
   - Criar novo projeto
   - Baixar novo google-services.json
   - Atualizar todas as configurações

2. **Verificar dependências:**
   ```yaml
   firebase_core: ^2.32.0
   firebase_auth: ^4.20.0
   google_sign_in: ^6.2.1
   ```

3. **Testar em emulador vs dispositivo real:**
   - Emulador pode ter limitações
   - Dispositivo real é mais confiável

---

## 📞 Quando Pedir Ajuda

**Forneça estas informações:**
- 📱 **Dispositivo e versão Android**
- 🔢 **Versão do app**
- 💬 **Mensagem de erro exata**
- 📊 **Logs do console (se disponíveis)**
- 🔍 **Passos para reproduzir o erro**

---

## ✅ Checklist de Verificação

- [ ] Conexão com internet funcionando
- [ ] Conta Google válida e ativa
- [ ] Google Play Services atualizado
- [ ] google-services.json atualizado
- [ ] SHA keys corretas no Firebase
- [ ] Permissões no AndroidManifest
- [ ] Dependências atualizadas
- [ ] Firebase Console configurado

**Lembre-se:** A maioria dos problemas de login são causados por configuração incorreta das chaves SHA ou problemas no google-services.json! 🔑