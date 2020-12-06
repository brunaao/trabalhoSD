package br.ufu.sd.client;

import br.ufu.sd.grpc.Saida;
import com.google.protobuf.ByteString;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.math.BigInteger;
import java.util.Base64;
import java.util.Random;

public class TestClient implements Runnable {
    private HashTableApi api;
    private int nroCliente;
    private int registros;
    private static final int N_TESTES = 1000;
    private static final int CHAVE_MAX = 2000;
    private static final int VERSAO_MAX = 3;

    public TestClient(Channel channel, int nroCliente) {
        api = new HashTableApi(channel);
        this.nroCliente = nroCliente;
    }

    public void startTests() {
        int insercoes = insercoes();
        System.out.println("==============================");
        atualizacoes();
        System.out.println("==============================");
        consulta();
        System.out.println("==============================");
        int exclusoes = excluir();
        System.out.println("==============================");
        registros = insercoes - exclusoes;
    }

    public int getRegistros() {
        return registros;
    }

    private int insercoes() {
        int diferentes = 0;
        for (int i = 0; i < N_TESTES; i++) {
            ByteString valor = ByteString.copyFrom(Base64.getEncoder().encode(BigInteger.valueOf(i).toByteArray()));
            long chave = (new Random().nextInt(CHAVE_MAX - 1)) + 1;
            Saida resultado = api.set(chave, valor);
            if (resultado == null) {
                System.out.println("Erro encontrado...");
                System.exit(0);
            }
            if (resultado.getValue().getTimeSt() == 0) {
                diferentes++;
            }
        }
        System.out.println(diferentes + " valores distintos foram inseridos e " + (N_TESTES - diferentes) + " foram atualizados no cliente " + nroCliente);
        return diferentes;
    }

    private void atualizacoes() {
        int erros = 0;
        int sucessos = 0;
        for (int i = 0; i < N_TESTES; i++) {
            ByteString valor = ByteString.copyFrom(Base64.getEncoder().encode(BigInteger.valueOf(i).toByteArray()));
            long chave = (new Random().nextInt(CHAVE_MAX - 1)) + 1;
            long versao = (new Random().nextInt(VERSAO_MAX - 1)) + 1;

            Saida resultado = api.testAndSet(chave, versao, valor);

            if (resultado == null) {
                System.out.println("Erro encontrado...");
                System.exit(0);
            }

            if (resultado.getError().equals("SUCCESS")) {
                sucessos++;
            } else {
                erros++;
            }
        }
        String resultados = "Resultados teste de atualizações do cliente " + nroCliente + ":\n"
                + "Atualizações bem sucedidas: " + sucessos + "\n"
                + "Atualizações inválidas: " + erros;
        System.out.println(resultados);
    }

    private void consulta() {
        int erros = 0;
        int sucessos = 0;
        for (int i = 0; i < N_TESTES; i++) {
            long chave = (new Random().nextInt(CHAVE_MAX - 1)) + 1;

            Saida resultado = api.get(chave);
            if (resultado == null) {
                System.out.println("Erro encontrado...");
                System.exit(0);
            }

            if (resultado.getError().equals("SUCCESS")) {
                sucessos++;
            } else {
                erros++;
            }
        }
        String resultados = "Resultados teste de consultas do cliente " + nroCliente + ":\n"
                + "Consultas bem sucedidas: " + sucessos + "\n"
                + "Consultas inválidas: " + erros;
        System.out.println(resultados);
    }

    private int excluir() {
        int erros = 0;
        int sucessos = 0;
        for (int i = 0; i < N_TESTES; i++) {
            long chave = (new Random().nextInt(CHAVE_MAX - 1)) + 1;

            Saida resultado = api.delK(chave);
            if (resultado == null) {
                System.out.println("Erro encontrado...");
                System.exit(0);
            }

            if (resultado.getError().equals("SUCCESS")) {
                sucessos++;
            } else {
                erros++;
            }
        }
        String resultados = "Resultados teste de exclusões do cliente " + nroCliente + ":\n"
                + "Exclusões bem sucedidas: " + sucessos + "\n"
                + "Exclusões inválidas: " + erros;
        System.out.println(resultados);
        return sucessos;
    }

    @Override
    public void run() {
        startTests();
    }

    public static void main(String[] args) throws InterruptedException {
        int n;
        int registros = 0;
        try {
            n = Integer.parseInt(args[0]);
            System.out.println("Executando " + n + " cliente(s) de teste.");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            n = 1;
            System.out.println("Executando 1 cliente de teste por padrão.");
        }

        TestClient[] clients = new TestClient[n];
        Thread[] testes = new Thread[n];
        String target = "localhost:50051";

        System.out.println("============TESTES============");
        for (int i = 0; i < n; i++) {
            ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                    .usePlaintext()
                    .build();
            clients[i] = new TestClient(channel, i + 1);
            testes[i] = new Thread(clients[i]);
            testes[i].start();
        }

        for (Thread t : testes) {
            t.join();
        }

        for (TestClient client : clients) {
            registros += client.getRegistros();
        }

        String resultado = "";
        if (registros < 0) {
            resultado += (registros * -1) + " registros foram removidos da";
        } else {
            resultado += registros + " registros foram adicionados à";
        }
        resultado += " base após a execução dos testes";
        if (registros == 0) {
            resultado = "Não houve alteração na quantidade de registros na base após a execução dos testes";
        }
        System.out.println(resultado);
    }
}
