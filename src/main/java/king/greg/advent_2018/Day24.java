package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day24 {

	List<Group> groups;
	List<Group> groupsBackup;

	class Group {
		@Override
		public String toString() {
			return "Group [effectivePower=" + effectivePower() + ", immuneSystem=" + immuneSystem + ", units=" + units
					+ ", hitPoints=" + hitPoints + ", attackDamage=" + attackDamage + ", attackType=" + attackType
					+ ", initiative=" + initiative + ", immunities=" + immunities + ", weaknesses=" + weaknesses + "]";
		}

		boolean immuneSystem;
		int units;
		int hitPoints;
		int attackDamage;
		String attackType;
		int initiative;
		Set<String> immunities;
		Set<String> weaknesses;

		Group target = null;
		boolean targetted = false;

		Group(final boolean immuneSystem, final int units, final int hitPoints, final int attackDamage,
				final String attackType, final int initiative) {
			this.immuneSystem = immuneSystem;
			this.units = units;
			this.hitPoints = hitPoints;
			this.attackDamage = attackDamage;
			this.attackType = attackType;
			this.initiative = initiative;
			immunities = new HashSet<String>();
			weaknesses = new HashSet<String>();
		}

		public int effectivePower() {
			return units * attackDamage;
		}

		public int getInitiative() {
			return initiative;
		}

		public int damageFrom(final Group aggressor) {
			if (immunities.contains(aggressor.attackType)) {
				return 0;
			} else if (weaknesses.contains(aggressor.attackType)) {
				return aggressor.effectivePower() * 2;
			}
			return aggressor.effectivePower();
		}

		public boolean attack() {
			if ((null == target) || (units <= 0)) {
				return false;
			}
			return target.takeDamageFrom(this);
		}

		public boolean takeDamageFrom(final Group group) {
			int lostUnits = damageFrom(group) / hitPoints;
			if (lostUnits > units) {
				lostUnits = units;
			}
			System.out.println("Killing " + lostUnits);
			units -= lostUnits;
			return (lostUnits > 0);
		}
	}

	Day24(final FileReader fileReader) {
		groups = new ArrayList<Group>();

		try {
			final BufferedReader buf = new BufferedReader(fileReader);
			boolean immuneSystem = false;
			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					switch (lineJustFetched) {
					case "Immune System:":
						immuneSystem = true;
						break;
					case "Infection:":
						immuneSystem = false;
						break;
					case "":
						break;
					default:
						String[] inputs = lineJustFetched
								.split(" units each with | hit points | an attack that does | damage at initiative ");
						int units = Integer.valueOf(inputs[0]);
						int hitPoints = Integer.valueOf(inputs[1]);
//						String[] weaknesses = inputs[2].split("; ");
						String[] damage = inputs[3].split(" ");
						int damageAmount = Integer.valueOf(damage[0]);
						String damageType = damage[1];
						int initiative = Integer.valueOf(inputs[4]);
						final Group group = new Group(immuneSystem, units, hitPoints, damageAmount, damageType,
								initiative);
						if (lineJustFetched.contains("(")) {

							String weaknessString = lineJustFetched.substring(lineJustFetched.indexOf('(') + 1,
									lineJustFetched.indexOf(')'));

							String[] weaknesses = weaknessString.split("; ");

							for (String weakness : weaknesses) {
								String[] list = weakness.split(" to |, ");
								for (int i = 1; i < list.length; i++) {
									switch (list[0]) {
									case "weak":
										group.weaknesses.add(list[i]);
										break;
									case "immune":
										group.immunities.add(list[i]);
										break;
									}
								}
							}
						}
						System.out.println(group);
						groups.add(group);
					}
				}
			}
		} catch (IOException ioe) {

		}
	}

	public int war() {
		while (groups.stream().anyMatch(group -> group.immuneSystem)
				&& groups.stream().anyMatch(group -> !group.immuneSystem)) {
			fight();
		}
		return groups.stream().mapToInt(group -> group.units).sum();
	}
	
	public int war2(final int boost) {
		groups.stream().filter(group -> group.immuneSystem).forEach(group-> group.attackDamage += boost);
		while (groups.stream().anyMatch(group -> group.immuneSystem)
				&& groups.stream().anyMatch(group -> !group.immuneSystem) && fight()) {
		}
		System.out.println(groups);
		if (groups.stream().anyMatch(group -> !group.immuneSystem)) {
			return -1;
		}
		return groups.stream().mapToInt(group -> group.units).sum();
	}

	public boolean fight() {
		System.out.println("------------");
		// Target Selection
		Collections.sort(groups, Comparator.comparing(Group::effectivePower, Comparator.reverseOrder())
				.thenComparing(Group::getInitiative, Comparator.reverseOrder()));
		for (Group group : groups) {
			for (Group potentialTarget : groups) {
				if ((potentialTarget.immuneSystem == group.immuneSystem) || potentialTarget.targetted) {
					continue;
				}
				if (null == group.target || potentialTarget.damageFrom(group) > group.target.damageFrom(group)) {
					group.target = potentialTarget;
				} else if ((potentialTarget.damageFrom(group) == group.target.damageFrom(group))
						&& (potentialTarget.effectivePower() > group.target.effectivePower())) {
					group.target = potentialTarget;
				}
				System.out.println("Would deal --- " + potentialTarget.damageFrom(group));
			}
			if (null!=group.target && group.target.damageFrom(group) == 0) {
				group.target = null;  // even though this isn't a rule in the stupid problem description...
			}
			if (null != group.target) {
				group.target.targetted = true;
			}
		}

		boolean didDamage = false;
		
		// Attacking phase
		Collections.sort(groups, Comparator.comparing(Group::getInitiative, Comparator.reverseOrder()));
		for (Group group : groups) {
			didDamage |= group.attack();
		}

		groups.removeIf(group -> group.units <= 0);
		groups.forEach(group -> group.target = null);
		groups.forEach(group -> group.targetted = false);
		
		return didDamage;
	}

}
