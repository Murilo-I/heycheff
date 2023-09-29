package br.edu.fatec.principal;

import java.util.Arrays;
import java.util.Scanner;

import br.edu.fatec.DAO.ReceitaDAO;
import br.edu.fatec.model.Receita;
import br.edu.fatec.model.Step;

public class Principal {

	private static Scanner sc;
	private static Receita[] arrayReceitas;
	private static ReceitaDAO receitaDAO;

	public Principal() {
		receitaDAO = new ReceitaDAO();
	}

	public static void main(String[] args) {

		Receita receita1 = new Receita("Ovo Frito");
		Step step1 = new Step("Quebrar ovo");
		step1.setListIngrediente(Arrays.asList("Ovo"));
		receita1.addListStep(step1);
		
		step1 = new Step("Colocar 15g de manteiga e um filete de azeite na frigideira e ligue o fogo baixo");
		step1.setListIngrediente(Arrays.asList("Manteiga", "Azeite"));
		receita1.addListStep(step1);
		
		step1 = new Step("Adicione Sal a gosto");
		step1.setListIngrediente(Arrays.asList("Sal"));
		receita1.addListStep(step1);
		
		step1 = new Step("Frite dos dois lados ao apenas de um, até o ovo ficar no ponto desejado");
		receita1.addListStep(step1);
		
		Step[] stepsReceita1 = { new Step(new String[] { "Ovo" }, "Quebrar ovo"),
				new Step(new String[] { "Manteiga", "Azeite" }, "Colocar 15g de manteiga e um filete de azeite na frigideira e ligue o fogo baixo"),
				new Step(new String[] { "Sal" }, "Adicione Sal a gosto"),
				new Step(new String[] {}, "Frite dos dois lados ao apenas de um, até o ovo ficar no ponto desejado") };

		receita1.setSteps(stepsReceita1);

		Receita receita2 = new Receita("Arroz Cozido");
		
		Step step2 = new Step("Coloque 500ml de água para ferver");
		step1.setListIngrediente(Arrays.asList("Água"));
		receita2.addListStep(step2);

		step2 = new Step("Coloque um fio de azeite em uma panela pequena, ligue em fogo baixo");
		step1.setListIngrediente(Arrays.asList("Azeite"));
		receita2.addListStep(step2);

		step2 = new Step("Adicione 2 alhos picados/espremidos e aguarde dourar");
		step1.setListIngrediente(Arrays.asList("Alho"));
		receita2.addListStep(step2);

		step2 = new Step("Adicione 200g de Arroz Branco e misture por 30 segundos");
		step1.setListIngrediente(Arrays.asList("Arroz Branco"));
		receita2.addListStep(step2);
		
		step2 = new Step("Adicione a água até 1cm acima do arroz");
		step1.setListIngrediente(Arrays.asList("Água"));
		receita2.addListStep(step2);

		step2 = new Step("Adicione sal a gosto");
		step1.setListIngrediente(Arrays.asList("Sal"));
		receita2.addListStep(step2);

		step2 = new Step("Aguarde a água secar");
		receita2.addListStep(step2);
		
		Step[] stepsReceita2 = { new Step(new String[] { "Água" }, "Coloque 500ml de água para ferver"),
				new Step(new String[] { "Azeite" }, "Coloque um fio de azeite em uma panela pequena, ligue em fogo baixo"),
				new Step(new String[] { "Alho" }, "Adicione 2 alhos picados/espremidos e aguarde dourar"),
				new Step(new String[] { "Arroz Branco" }, "Adicione 200g de Arroz Branco e misture por 30 segundos"),
				new Step(new String[] { "Água" }, "Adicione a água até 1cm acima do arroz"), 
				new Step(new String[] { "Sal" }, "Adicione sal a gosto"),
				new Step(new String[] {}, "Aguarde a água secar") };

		receita2.setSteps(stepsReceita2);

		addReceita(receita1);
		addReceita(receita2);

		String menuPrincipal = "\n=== MENU ===\n1. Listar receitas\n2. Cadastrar receita\n3. Editar receita\n4. Excluir receita\n0. Sair\nSelecione uma opção da lista";
		int opcaoMenu = -1;
		while (opcaoMenu != 0) {
			System.out.println(menuPrincipal);
			try {
				sc = new Scanner(System.in);
				opcaoMenu = sc.nextInt();
				if (opcaoMenu > 4 || opcaoMenu < 0) {
					System.out.println("Digite uma das opções do menu");
				} else {
					switch (opcaoMenu) {
					case 1:
						System.out.println("\n<< OPÇÃO 1 - LISTAR RECEITAS >>\n");
						listarListaReceitas(arrayReceitas);
						System.out.println("0. Cancelar");
						int opcaoReceitaListar = -1;
						while (opcaoReceitaListar != 0) {
							sc = new Scanner(System.in);
							try {
								opcaoReceitaListar = sc.nextInt();
								if (opcaoReceitaListar < 1 || opcaoReceitaListar > arrayReceitas.length) {
									if (opcaoReceitaListar != 0)
										System.out.println("Digite uma das opções do menu");
								} else {
									for (int a = 0; a < arrayReceitas[opcaoReceitaListar - 1].getSteps().length; a++) {
										if (arrayReceitas[opcaoReceitaListar - 1].getSteps()[a] == null)
											break;
										System.out.println((a + 1) + ". " + arrayReceitas[opcaoReceitaListar - 1].getSteps()[a].getDescricao());
										for (int b = 0; b < arrayReceitas[opcaoReceitaListar - 1].getSteps()[a].ingredientes.length; b++) {
											System.out.println("  " + (b + 1) + ". " + arrayReceitas[opcaoReceitaListar - 1].getSteps()[a].ingredientes[b]);
										}
									}
									opcaoReceitaListar = 0;
								}
							} catch (Exception e) {
								System.out.println("Digite uma opção válida");
							}
						}
						break;
					case 2:
						System.out.println("\n<< OPÇÃO 2 - CADASTRAR RECEITA >>\n");
						System.out.println("Digite a descrição da sua receita");
						Receita receita = new Receita(new Scanner(System.in).next());
						adicionarPassos(receita);
						addReceita(receita);
						break;
					case 3:
						System.out.println("\n<< OPÇÃO 3 - EDITAR RECEITA >>\n");
						listarListaReceitas(arrayReceitas);
						System.out.println("0. Cancelar");
						int opcaoReceitaAtualizar = -1;
						while (opcaoReceitaAtualizar != 0) {
							sc = new Scanner(System.in);
							try {
								opcaoReceitaAtualizar = sc.nextInt();
								if (opcaoReceitaAtualizar < 1 || opcaoReceitaAtualizar > arrayReceitas.length) {
									if (opcaoReceitaAtualizar != 0)
										System.out.println("Digite uma das opções do menu para fazer a edição");
								} else {
									System.out.println("Digite a nova descrição da receita");
									Receita receitaEditar = new Receita(new Scanner(System.in).next());
									adicionarPassos(receitaEditar);
									arrayReceitas[opcaoReceitaAtualizar - 1] = receitaEditar;
									System.out.println("Edição finalizada");
									opcaoReceitaAtualizar = 0;
								}
							} catch (Exception e) {
								System.out.println("Digite uma opção válida");
							}
						}
						break;
					case 4:
						System.out.println("\n<< OPÇÃO 4 - EXCLUIR RECEITA >>\n");
						listarListaReceitas(arrayReceitas);
						System.out.println("0. Cancelar");
						int opcaoReceitaDeletar = -1;
						while (opcaoReceitaDeletar != 0) {
							sc = new Scanner(System.in);
							try {
								opcaoReceitaDeletar = sc.nextInt();
								if (opcaoReceitaDeletar < 1 || opcaoReceitaDeletar > arrayReceitas.length) {
									if (opcaoReceitaDeletar != 0)
										System.out.println("Digite uma das opções do menu para apagar");
								} else {
									Receita[] novaLista = arrayReceitas.clone();
									arrayReceitas = null;
									for (int i = 0; i < novaLista.length; i++) {
										if (i != opcaoReceitaDeletar - 1) {
											addReceita(novaLista[i]);
										}
									}
									System.out.println("Edição finalizada");
									opcaoReceitaDeletar = 0;
								}
							} catch (Exception e) {
								System.out.println("Digite uma opção válida");
							}
						}
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Digite uma opção válida");
			}
		}
	}

	private static void adicionarPassos(Receita receita) {
		boolean opcaoFinalizarSteps = true;
		int sequenciaPassos = 1;
		do {

			System.out.println("Digite o passo número " + sequenciaPassos);
			Step step = new Step(new Scanner(System.in).next());

			boolean opcaoMaisIngredintes = true;
			int sequenciaIngredientes = 1;
			do {
				System.out.println("Digite o ingrediente " + sequenciaIngredientes);
				step.addIngrediente(new Scanner(System.in).next());
				sequenciaIngredientes++;
				System.out.println("Deseja adicionar mais um ingrediente?\nDigite uma das seguintes opções:\n0. Não\n1. Sim");
				int finalizarIngredientes = -1;
				while (finalizarIngredientes != 0 && finalizarIngredientes != 1) {
					try {
						finalizarIngredientes = new Scanner(System.in).nextInt();
						if (finalizarIngredientes == 0)
							opcaoMaisIngredintes = false;
					} catch (Exception e) {
						System.out.println("Digite uma opção válida");
					}
				}
			} while (opcaoMaisIngredintes);

			sequenciaPassos++;

			System.out.println("Deseja adicionar mais um passo?\nDigite uma das seguintes opções:\n0. Não\n1. Sim");
			int finalizarPasso = -1;
			while (finalizarPasso != 0 && finalizarPasso != 1) {
				try {
					finalizarPasso = new Scanner(System.in).nextInt();
					if (finalizarPasso == 0)
						opcaoFinalizarSteps = false;
				} catch (Exception e) {
					System.out.println("Digite uma opção válida");
				}
			}

			receita.addStep(step);

		} while (opcaoFinalizarSteps);
	}

	private static void listarListaReceitas(Receita[] arrayReceita) {
		System.out.println("\nSelecione uma das receitas");
		for (int i = 0; i < arrayReceita.length; i++) {
			if (arrayReceita[i] == null)
				break;
			System.out.println((i + 1) + ". " + arrayReceita[i].getDescricao());
		}
	}

	public static void addReceita(Receita receita) {
		Receita[] novaReceita;
		if (Principal.arrayReceitas == null) {
			novaReceita = new Receita[1];
			novaReceita[0] = receita;
		} else {
			novaReceita = new Receita[Principal.arrayReceitas.length + 1];
			for (int i = 0; i < Principal.arrayReceitas.length; i++) {
				novaReceita[i] = Principal.arrayReceitas[i];
			}
			novaReceita[novaReceita.length - 1] = receita;
		}
		Principal.arrayReceitas = novaReceita;
	}
}
