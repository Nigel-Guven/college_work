class Item():
	def __init__(self, v ,w):
		self.value = v
		self.weight = w
		
	def __repr__(self):
		return "({}, {})".format(self.value, self.weight)
		
def ks(capacity, items, memo):
	assert capacity >= 0
	if capacity == 0:
		return 0
	else:
		if capacity in memo:
			return memo[capacity]
		else:
			options = [[item.value + ks(capacity - item.weight,items,memo) for item in items if item.weight <= capacity]
			if length(options) == 0:
				return 0
			else:
				memo[capacity] = max(options)
				return memo[capacity]


def main(args):
	if len(args) < 2:
		print("Usage ks <total> <sequence of item value/weight pairs>")
	else:
		capacity = int(args[1])
		values = [int(v) for v in args[2::2]]
		weights = [int(w) for w in args[3::2]]
		items = [Item(v,w) for v,w in zip(values, weights)]
		print(capacity, values, weights)
		
		print("The best value is {}".format(ks(capacity,items)))
		
		
import sys

if __name__ == "__main__":
	main(sys.argv)