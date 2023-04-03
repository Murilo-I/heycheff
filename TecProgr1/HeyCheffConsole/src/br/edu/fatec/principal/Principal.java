package br.edu.fatec.principal;

import java.util.Scanner;

import br.edu.fatec.model.Receita;
import br.edu.fatec.model.Step;

public class Principal {

	private static Scanner sc;

	// private static String[][] receitas = new String[10][6];
	// private static int numReceitas = 0;

	public static void main(String[] args) {

		Receita receita1 = new Receita("Ovo Frito");
		Step[] stepsReceita1 = { 
				new Step(new String[] { "Ovo" }, "Quebrar ovo"),
				new Step(new String[] { "Manteiga", "Azeite" }, "Colocar 15g de manteiga e um filete de azeite na frigideira e ligue o fogo baixo"),
				new Step(new String[] { "Sal" }, "Adicione Sal a gosto"),
				new Step(new String[] {}, "Frite dos dois lados ao apenas de um, até o ovo ficar no ponto desejado") };

		receita1.setSteps(stepsReceita1);

		Receita receita2 = new Receita("Arroz Cozido");
		Step[] stepsReceita2 = { 
				new Step(new String[] { "Água" }, "Coloque 500ml de água para ferver"),
				new Step(new String[] { "Azeite" }, "Coloque um fio de azeite em uma panela pequena, ligue em fogo baixo"),
				new Step(new String[] { "Alho" }, "Adicione 2 alhos picados/espremidos e aguarde dourar"),
				new Step(new String[] { "Arroz Branco" }, "Adicione 200g de Arroz Branco e misture por 30 segundos"),
				new Step(new String[] { "Água" }, "Adicione a água até 1cm acima do arroz"), new Step(new String[] { "Sal" }, "Adicione sal a gosto"),
				new Step(new String[] {}, "Aguarde a água secar") };

		receita2.setSteps(stepsReceita2);

		Receita[] arrayReceita = new Receita[100];
		arrayReceita[0] = receita1;
		arrayReceita[1] = receita2;

		for (int i = 0; i < arrayReceita.length; i++) {
			if (arrayReceita[i] == null)
				break;
			System.out.println((i + 1) + " - " + arrayReceita[i].getDescricao());
			for (int a = 0; a < arrayReceita[i].getSteps().length; a++) {
				if (arrayReceita[i].getSteps()[a] == null)
					break;
				System.out.println((a + 1) + " - " + arrayReceita[i].getSteps()[a].getDescricao());
			}
			System.out.println(" ");
		}

		String menuPrincipal = "\n=== MENU ===\n1. Listar receitas\n2. Cadastrar receita\n3. Editar receita\n4. Excluir receita\n0. Sair\nSelecione uma opção da lista";
		int opcaoMenu = -1;
		while (opcaoMenu != 0) {
			System.out.println(menuPrincipal);

			sc = new Scanner(System.in);
			//try {
				opcaoMenu = sc.nextInt();
				if (opcaoMenu > 4 || opcaoMenu < 0) {
					System.out.println("Digite uma das opções do menu");
				} else {
					switch (opcaoMenu) {
					case 1:
						ListarListaReceitas(arrayReceita);
						int opcaoReceitaListar = -1;
						while (opcaoReceitaListar != 0) {
							sc = new Scanner(System.in);
							try {
								opcaoReceitaListar = sc.nextInt();
								if (opcaoReceitaListar < 0 || opcaoReceitaListar > arrayReceita.length) {
									System.out.println("Digite uma das opções do menu");
								} else {
									for (int a = 0; a < arrayReceita[opcaoReceitaListar - 1].getSteps().length; a++) {
										if (arrayReceita[opcaoReceitaListar - 1].getSteps()[a] == null)
											break;
										System.out.println((a + 1) + " - " + arrayReceita[opcaoReceitaListar - 1].getSteps()[a].getDescricao());
										for (int b = 0; b < arrayReceita[opcaoReceitaListar - 1].getSteps()[a].ingredientes.length; b++) {
											System.out.println("  " + (b + 1) + ". " + arrayReceita[opcaoReceitaListar - 1].getSteps()[a].ingredientes[b]);
										}
									}
								}
								opcaoReceitaListar = 0;
							} catch (Exception e) {
								System.out.println("Digite uma opção válida");
							}
						}
						break;
					case 2:
						//ListarListaReceitas(arrayReceita);
						System.out.println("Digite a descrição da sua receita");
						//sc = new Scanner(System.in);
						Receita receita = new Receita(new Scanner(System.in).next());
						receita.addStep(new Step(new String[] { "Água" }, "Coloque 500ml de água para ferver"));
						receita.addStep(new Step(new String[] { "Água" }, "Coloque 500ml de água para ferver22"));
						
						for (Step step : receita.getSteps()) {
							System.out.println(step.getDescricao());
						}
						
						
						int opcaoFinalizarSteps = -1;
//						
//						
//						
//						do {
//							
//							System.out.println("Deseja adicionar mais um passo?\nDigite uma das seguintes opções:\n0. Não\n1. Sim");
//							try {
//								opcaoFinalizarSteps = new Scanner(System.in).nextInt();
//							} catch (Exception e) {
//								System.out.println("Digite uma opção válida");
//							}
//						} while (opcaoFinalizarSteps != 0);
						//Step step = new Step(new String[] { "Água" }, "Coloque 500ml de água para ferver");
						
						break;
					case 3:
						break;
					case 4:
						break;
					}
				}
//			} catch (Exception e) {
//				System.out.println("Digite uma opção válida");
//			}
		}

//		while (true) {
//			System.out.println("\n=== MENU ===");
//			System.out.println("1. Listar receitas");
//			System.out.println("2. Cadastrar receita");
//			System.out.println("3. Editar receita");
//			System.out.println("4. Excluir receita");
//			System.out.println("5. Listar ingredientes de uma receita");
//			System.out.println("6. Editar ingredientes de uma receita");
//			System.out.println("7. Sair");
//
//			System.out.print("\nEscolha uma opção(Números de 1 a 7): ");
//			int opcao = sc.nextInt();
//
//			switch (opcao) {
//			case 1:
//				listarReceitas();
//				break;
//			case 2:
//				cadastrarReceita();
//				break;
//			case 3:
//				editarReceita();
//				break;
//			case 4:
//				excluirReceita();
//				break;
//			case 5:
//				listarIngredientes();
//				break;
//			case 6:
//				editarIngredientes();
//				break;
//			case 7:
//				System.out.println("");
//				System.out.println("Encerrando o programa...");
//				System.out.println("Programa encerrado com sucesso!");
//				System.exit(0);
//
//				break;
//			default:
//				System.out.println(
//						"Opção inválida, Por favor insira um número que seja válido dentro das opções listadas!");
//			}
//		}
	}

	private static void ListarListaReceitas(Receita[] arrayReceita) {
		System.out.println("\nSelecione uma das receitas");
		for (int i = 0; i < arrayReceita.length; i++) {
			if (arrayReceita[i] == null)
				break;
			System.out.println((i + 1) + " - " + arrayReceita[i].getDescricao());
		}
	}

//	private static void listarReceitas() {
//		if (numReceitas == 0) {
//			System.out.println("Nenhuma receita cadastrada.");
//			return;
//		}
//
//		System.out.println("\n=== LISTA DE RECEITAS ===");
//		for (int i = 0; i < numReceitas; i++) {
//			System.out.println((i + 1) + ". " + receitas[i][1]);
//		}
//	}
//
//	private static void cadastrarReceita() {
//		Scanner sc = new Scanner(System.in);
//
//		System.out.println("\n=== CADASTRAR RECEITA ===");
//		System.out.print("Nome da receita: ");
//		String nome = sc.nextLine();
//
//		System.out.print("ID da pessoa responsável pela receita: ");
//		String id = sc.nextLine();
//
//		receitas[numReceitas][0] = Integer.toString(numReceitas + 1);
//		receitas[numReceitas][1] = nome;
//		receitas[numReceitas][2] = id;
//
//		System.out.print("Quantidade de ingredientes: ");
//		int numIngredientes = sc.nextInt();
//
//		for (int i = 1; i <= numIngredientes; i++) {
//			System.out.print("\nIngrediente " + i + ": ");
//			String ingrediente = sc.next();
//			receitas[numReceitas][2 + i] = ingrediente;
//		}
//
//		numReceitas++;
//		System.out.println("\nReceita cadastrada com sucesso!");
//	}
//
//	private static void editarReceita() {
//		Scanner sc = new Scanner(System.in);
//
//		System.out.println("\n=== EDITAR RECEITA ===");
//		System.out.print("Digite o número da receita que deseja editar: ");
//		int num = sc.nextInt();
//
//		if (num < 1 || num > numReceitas) {
//			System.out.println("Receita não encontrada.");
//			return;
//		}
//		System.out.print("Novo nome da receita: ");
//		String nome = sc.next();
//
//		receitas[num - 1][1] = nome;
//
//		System.out.println("Receita editada com sucesso.");
//	}
//
//	private static void excluirReceita() {
//		Scanner sc = new Scanner(System.in);
//
//		System.out.println("\n=== EXCLUIR RECEITA ===");
//		System.out.print("Digite o número da receita que deseja excluir: ");
//		int num = sc.nextInt();
//
//		if (num < 1 || num > numReceitas) {
//			System.out.println("Receita não encontrada.");
//			return;
//		}
//
//		for (int i = num - 1; i < numReceitas - 1; i++) {
//			receitas[i][0] = receitas[i + 1][0];
//			receitas[i][1] = receitas[i + 1][1];
//			receitas[i][2] = receitas[i + 1][2];
//			receitas[i][3] = receitas[i + 1][3];
//			receitas[i][4] = receitas[i + 1][4];
//			receitas[i][5] = receitas[i + 1][5];
//		}
//
//		numReceitas--;
//		System.out.println("Receita excluída com sucesso.");
//	}
//
//	private static void listarIngredientes() {
//		Scanner sc = new Scanner(System.in);
//
//		System.out.println("\n=== LISTAR INGREDIENTES DE UMA RECEITA ===");
//		System.out.print("Digite o número da receita: ");
//		int num = sc.nextInt();
//
//		if (num < 1 || num > numReceitas) {
//			System.out.println("Receita não encontrada.");
//			return;
//		}
//
//		System.out.println("Ingredientes da receita " + receitas[num - 1][1] + ":");
//
//		for (int i = 3; i < receitas[num - 1].length; i++) {
//			if (receitas[num - 1][i] != null) {
//				System.out.println("- " + receitas[num - 1][i]);
//			}
//		}
//	}
//
//	private static void editarIngredientes() {
//		Scanner sc = new Scanner(System.in);
//
//		System.out.println("\n=== EDITAR INGREDIENTES DE UMA RECEITA ===");
//		System.out.print("Digite o número da receita que deseja editar os ingredientes: ");
//		int num = sc.nextInt();
//
//		if (num < 1 || num > numReceitas) {
//			System.out.println("Receita não encontrada.");
//			return;
//		}
//
//		System.out.println("Ingredientes da receita " + receitas[num - 1][1] + ":");
//
//		for (int i = 3; i < receitas[num - 1].length; i++) {
//			if (receitas[num - 1][i] != null) {
//				System.out.println((i - 2) + ". " + receitas[num - 1][i]);
//			}
//		}
//
//		System.out.print("Digite o número do ingrediente que deseja editar: ");
//		int numIngrediente = sc.nextInt();
//
//		if (numIngrediente < 1 || numIngrediente > receitas[num - 1].length - 3
//				|| receitas[num - 1][numIngrediente + 2] == null) {
//			System.out.println("Ingrediente não encontrado.");
//			return;
//		}
//	}
}
