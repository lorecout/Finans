# 📱 Configuração do Sistema de Assinaturas Premium

## 🎯 Visão Geral
Este guia explica como configurar completamente o sistema de assinaturas premium do app Finans na Google Play Console.

## 📋 Pré-requisitos
- Conta de desenvolvedor Google Play Console
- App publicado ou em fase de teste
- Chaves de upload configuradas

## 🚀 Passo 1: Configurar Produto In-App

### 1.1 Acesse a Play Console
1. Entre em [Google Play Console](https://play.google.com/console)
2. Selecione seu app "Finans"

### 1.2 Criar Produto
1. No menu lateral, clique em **"Monetização"** → **"Produtos no app"**
2. Clique em **"Criar produto"**
3. Preencha os dados:

```
ID do produto: premium_monthly
Nome: Plano Premium Mensal
Descrição: Remova anúncios e tenha acesso a recursos premium
Preço: R$ 9,90 (ou o valor desejado)
```

### 1.3 Configurações Avançadas
- **Tipo**: Assinatura (recomendado para recorrência mensal)
- **Período de cobrança**: Mensal
- **Teste gratuito**: Opcional (ex: 3 dias)
- **Renovação automática**: Ativada

## 🧪 Passo 2: Configurar Testes

### 2.1 Lista de Testadores
1. Vá para **"Configurações"** → **"Licenciamento para testes"**
2. Adicione emails dos testadores
3. Configure **"Lista de teste interno"**

### 2.2 Testar Compras
Use estes IDs de teste no código durante desenvolvimento:
- `android.test.purchased` - Compra bem-sucedida
- `android.test.canceled` - Compra cancelada
- `android.test.refunded` - Reembolso
- `android.test.item_unavailable` - Item indisponível

## 🔧 Passo 3: Configuração Técnica

### 3.1 Verificar Código
O código já está configurado com:
- ✅ Produto ID: `premium_monthly`
- ✅ Permissões no AndroidManifest.xml
- ✅ Tratamento de compras e restauração
- ✅ Integração com banco de dados local

### 3.2 Arquivos de Configuração
- `lib/services/iap_config.dart` - Configurações dos produtos
- `lib/services/iap_service.dart` - Lógica de compras
- `lib/db_assinatura_helper.dart` - Persistência do status premium

## 📊 Passo 4: Monitoramento

### 4.1 Métricas no Play Console
- Acesse **"Monetização"** → **"Relatórios"**
- Monitore conversões e receita
- Acompanhe cancelamentos

### 4.2 Logs no App
O app registra automaticamente:
- Tentativas de compra
- Status das transações
- Erros encontrados

## 🐛 Passo 5: Troubleshooting

### Problemas Comuns:

**"Produto não encontrado"**
- Verifique se o ID do produto está correto
- Confirme se o produto está ativo na Play Console
- Teste com IDs de teste primeiro

**"Compras indisponíveis"**
- Verifique se o dispositivo tem Google Play Store
- Confirme se está logado com conta de teste
- Teste em dispositivo físico (não emulador)

**"Falha na validação"**
- Verifique conexão com internet
- Confirme se o app está assinado corretamente
- Teste com compras de teste

## 🎉 Passo 6: Publicação

### 6.1 Preparar Release
1. Configure preços reais
2. Desative produtos de teste
3. Atualize descrições para produção

### 6.2 Lançar Atualização
1. Faça upload do APK de produção
2. Configure rollout gradual
3. Monitore métricas pós-lançamento

## 📞 Suporte
Para dúvidas específicas:
- Documentação Google: [In-app Billing](https://developer.android.com/google/play/billing)
- Suporte Play Console: [Ajuda](https://support.google.com/googleplay/android-developer)

## ✅ Checklist Final
- [ ] Produto criado na Play Console
- [ ] Preços configurados
- [ ] Testes realizados
- [ ] Lista de testadores configurada
- [ ] App testado em produção
- [ ] Monitoramento ativo

---
**💡 Dica**: Comece sempre testando com os IDs de teste antes de usar produtos reais!